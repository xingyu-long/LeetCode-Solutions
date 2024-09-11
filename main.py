import os
import re
from sphinx.application import Sphinx
from sphinx.util.docutils import SphinxDirective
from docutils import nodes
from docutils.parsers.rst import directives
import shutil

# Custom directive for code blocks
class LeetCodeSolution(SphinxDirective):
    has_content = True
    required_arguments = 0
    optional_arguments = 1
    final_argument_whitespace = True
    option_spec = {
        'language': directives.unchanged_required,
    }

    def run(self):
        language = self.options['language']
        code = '\n'.join(self.content)
        literal = nodes.literal_block(code, code)
        literal['language'] = language
        return [literal]

def setup(app):
    app.add_directive('leetcode-solution', LeetCodeSolution)
    return {
        'version': '0.1',
        'parallel_read_safe': True,
        'parallel_write_safe': True,
    }

def format_problem_title(problem_dir):
    match = re.match(r'(\d+)_(.+)', problem_dir)
    if match:
        number, name = match.groups()
        name = ' '.join(word.capitalize() for word in name.split('_'))
        return f"{int(number)}. {name}"
    else:
        return ' '.join(word.capitalize() for word in problem_dir.split('_'))

def generate_rst_files(solutions_dir, output_dir):
    for root, dirs, files in os.walk(solutions_dir):
        if any(file.endswith(('.py', '.java', '.cpp', '.js')) for file in files):
            relative_path = os.path.relpath(root, solutions_dir)
            problem_dir = os.path.join(output_dir, relative_path)
            os.makedirs(problem_dir, exist_ok=True)
            
            problem_title = format_problem_title(os.path.basename(root))
            rst_file = os.path.join(problem_dir, f"{problem_title}.rst")
            
            with open(rst_file, 'w') as f:
                f.write(f"{problem_title}\n")
                f.write("=" * len(problem_title) + "\n\n")
                
                for solution_file in files:
                    if solution_file.endswith(('.py', '.java', '.cpp', '.js')):
                        language = os.path.splitext(solution_file)[1][1:]
                        with open(os.path.join(root, solution_file), 'r') as sol_file:
                            content = sol_file.read()
                            f.write(f"{language.capitalize()} Solution\n")
                            f.write("-" * (len(language) + 9) + "\n\n")
                            f.write(f".. code-block:: {language}\n\n")
                            f.write("   " + content.replace("\n", "\n   ") + "\n\n")

def generate_index(solutions_dir, output_dir):
    def create_toctree(directory, level=0):
        content = []
        dirs = []
        files = []
        
        for item in sorted(os.listdir(directory)):
            item_path = os.path.join(directory, item)
            if os.path.isdir(item_path):
                dirs.append(item)
            elif item.endswith('.rst') and item != 'index.rst':
                files.append(item)
        
        if files or dirs:
            content.append(f"{'   ' * level}.. toctree::")
            content.append(f"{'   ' * level}   :maxdepth: 1")
            content.append("")
        
        for file in files:
            name = os.path.splitext(file)[0]
            content.append(f"{'   ' * (level+1)}{name}")
        
        for dir in dirs:
            dir_path = os.path.join(directory, dir)
            content.append(f"{'   ' * (level+1)}{dir}/index")
            
            # Create index.rst for subdirectory
            subdir_index = os.path.join(dir_path, 'index.rst')
            with open(subdir_index, 'w') as f:
                f.write(f"{dir}\n")
                f.write("=" * len(dir) + "\n\n")
                f.write("\n".join(create_toctree(dir_path, level+1)))
        
        return content

    # Write main index.rst
    with open(os.path.join(output_dir, 'index.rst'), 'w') as f:
        f.write("LeetCode Solutions\n")
        f.write("==================\n\n")
        f.write("\n".join(create_toctree(output_dir)))

def create_solution_structure(solutions_dir, output_dir):
    file_suffix = {
        '.py',
        '.java',
    }

    problem_number_to_path = dict()
    for root, dirs, files in os.walk(solutions_dir):
        for file in files:
            for suffix in file_suffix:
                if suffix in file:  
                    # remove the suffix
                    file_name = file.removesuffix(suffix)
                    file_path = os.path.join(root, file)
                    print(file_path, file_name)
                    
                    
                    if not file_name.startswith('_'):
                        continue
                    problem_number = file_name.split('_')[1]
                    if problem_number in problem_number_to_path:
                        # # copy the file to the directory
                        shutil.copy(file_path, os.path.join(problem_number_to_path[problem_number], f"solution{suffix}"))
                        continue

                    print(file_path, file_name, problem_number)

                    relative_path = os.path.relpath(file_path, solutions_dir)
                    relative_path = "/".join(relative_path.split(os.sep)[:-1])
                    problem_dir = os.path.join(output_dir, relative_path)
                    print(problem_dir)

                    # create the directory
                    final_path = os.path.join(problem_dir, file_name)
                    # print("XXX", final_path)
                    os.makedirs(final_path, exist_ok=True)
                    # # copy the file to the directory
                    shutil.copy(file_path, os.path.join(final_path, file))
                    problem_number_to_path[problem_number] = final_path
    


if __name__ == "__main__":
    # solutions_dir = os.path.join(os.path.dirname(__file__), "leetcode", "src", "com", "leetcode")
    # # # solutions_dir = os.path.join(os.path.dirname(__file__), "leetcode", "src", "com", "leetcode")
    # output_dir = "organized_solutions"

    # create_solution_structure(solutions_dir, output_dir)
    # import sys
    # sys.exit()

    solutions_dir = "organized_solutions"
    # solutions_dir = "leetcode_solutions"
    output_dir = "source"
    build_dir = "build"

    if os.path.exists(output_dir):
        shutil.rmtree(output_dir)

    os.makedirs(output_dir, exist_ok=True)
    os.makedirs(build_dir, exist_ok=True)

    generate_rst_files(solutions_dir, output_dir)
    generate_index(output_dir, output_dir)

    # Create conf.py
    with open(os.path.join(output_dir, 'conf.py'), 'w') as f:
        f.write("project = 'LeetCode Solutions'\n")
        f.write("copyright = '2023, Your Name'\n")
        f.write("author = 'Your Name'\n\n")
        f.write("extensions = ['sphinx.ext.autodoc']\n\n")
        f.write("html_theme = 'alabaster'\n")

    # Run Sphinx
    app = Sphinx(output_dir, output_dir, build_dir, build_dir, "html")
    app.build()

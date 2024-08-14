import logging
from time import sleep
from threading import Condition, Thread, Semaphore, Barrier


logging.basicConfig(
    format="%(asctime)s %(levelname)-3s %(message)s",
    level=logging.INFO,
    datefmt="%Y-%m-%d %H:%M:%S",
)


def run_condition_example():
    shared_resource = []
    condition = Condition()

    def consumer():
        with condition:
            while not shared_resource:
                logging.info("Consumer is waiting...")
                condition.wait()
            item = shared_resource.pop()
            logging.info(f"Consumer consumed item:{item}")

    def producer():
        with condition:
            item = "new item"
            shared_resource.append(item)
            logging.info(f"Producer produced item:{item}")
            condition.notify()

    c_thread = Thread(target=consumer)
    p_thread = Thread(target=producer)
    c_thread.start()
    p_thread.start()


def run_semaphores_example():
    items = list(range(10))
    semaphore = Semaphore(3)

    def process_item(item):
        with semaphore:
            sleep(3)
            logging.info(f"Processing item {item}")

    threads = [Thread(target=process_item, args=(item,)) for item in items]
    for t in threads:
        t.start()
    for t in threads:
        t.join()


def run_barrier_example():
    items = list(range(9))
    barrier = Barrier(3)

    def process_item(item):
        logging.info(f"item {item} started")
        logging.info(f"item {item} reached the barrier")
        barrier.wait()
        logging.info(f"item {item} finished")

    threads = [Thread(target=process_item, args=(item,)) for item in items]
    for t in threads:
        t.start()
    for t in threads:
        t.join()


run_condition_example()
# run_semaphores_example()
# run_barrier_example()

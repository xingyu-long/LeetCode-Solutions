# /**
# * At Stripe, we send a lot of invoices. For each invoice, we send multiple reminder emails.
# For this question, you will be outputting the subject line of each email we send for customers'
# invoices in sorted order. (Use any library/tool for sorting).
# *
# * Your invoicing system will need to be able to configure a reminder schedule.
# For example, you might want to send one email 10 days before the invoice comes out,
# one email when the invoice comes out, one email 10 days before the invoice is due, and
# one email when the invoice is due, which is 30 days from when the invoice first came out.
# This send schedule could look something like this, corresponding to the diagram below:
# *
# * ```
# *      │
# *      │
# * t=-10│[Upcoming] Invoice for Alice
# *      │
# *      │
# *      │
# * t=0  │[New] Invoice for Alice
# *      │
# *      │
# *      │
# *      │
# *      │
# *      │
# * t=20 │[Reminder] Invoice for Alice
# *      │
# *      │
# *      │
# * t=30 │[Due] Invoice for Alice
# *      │
# *      │
# *      │
# *      │
# *      ▼
# * ```
# *
# * Given a configuration for sending emails, your input will be an unsorted list of unique
#  customer invoices with times for when their `[New]` invoice should come out and how much
#  we are charging them in dollars. Your objective is to print out the subject lines of all
# emails we will send out in sorted order by time. Your output should include the time, the
# type of email, the customer, and amount due.
# *
# * Here is a sample of how your system should behave:
# *
# * ```
# * send_schedule = {
# *   -10: "Upcoming",
# *   0: "New",
# *   20: "Reminder",
# *   30: "Due"
# * }
# *
# * invoicer = Invoicer.new(send_schedule)
# *
# * customer_invoices = [
# *     {"invoice_time": 0, "name": "Alice", "amount": 200},
# *     {"invoice_time": 1, "name": "Bob", "amount": 100},
# * ]
# * invoicer.send_emails(customer_invoices)
# * ```
# *
# * Output:
# * ```
# * -10: [Upcoming] Invoice for Alice for 200 dollars
# * -9: [Upcoming] Invoice for Bob for 100 dollars
# * 0: [New] Invoice for Alice for 200 dollars
# * 1: [New] Invoice for Bob for 100 dollars
# * 20: [Reminder] Invoice for Alice for 200 dollars
# * 21: [Reminder] Invoice for Bob for 100 dollars
# * 30: [Due] Invoice for Alice for 200 dollars
# * 31: [Due] Invoice for Bob for 100 dollars
# * ```
# * # Part 2
# * Customers sometimes make a series of payments to pay their invoice.
# In this part, you will have an unsorted list of payments made by the customers,
# specifying their name, time of payment, and payment amount. Each subject line should
#  now accurately reflect how much money is still owed by the customer. For example, if
# Bob pays half of his invoice right when it comes out, his next reminder email will say
# that he owes 50 dollars, not 100. If a customer has fully paid their invoice, we do not
# want to send them any more emails. In addition to returning the sequence of emails sent,
#  you will also need to return a list of delinquent customers, those who did not fully pay
#  their invoice before the due date, and how much they owe.
# *
# *
# * ```
# * customer_payments = [
# *     {"payment_time": -9, "name": "Alice", "amount": 100},
# *     {"payment_time": 1, "name": "Alice", "amount": 50},
# *     {"payment_time": 0, "name": "Bob", "amount": 100},
# * ]
# * invoicer.send_emails(customer_invoices, customer_payments)
# * ```
# *
# * Output:
# * ```
# * -10: [Upcoming] Invoice for Alice for 200 dollars
# * -9: [Upcoming] Invoice for Bob for 100 dollars
# * 0: [New] Invoice for Alice for 100 dollars
# * 20: [Reminder] Invoice for Alice for 50 dollars
# * 30: [Due] Invoice for Alice for 50 dollars
# * Delinquent customers:
# * Alice owes 50 dollars
# * ```
# */

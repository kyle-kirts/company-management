| Number of Classes | Line Coverage | Mutation Coverage | Test Strength |
| ----------------- | ------------- | ----------------- | ------------- |
| 5                 | 98% (259/263) | 98% (145/148)     | 98% (145/148) |

| Name               | Line Coverage | Mutation Coverage | Test Strength |
| ------------------ | ------------- | ----------------- | ------------- |
| Company.java       | 97% (100/103) | 96% (65/68)       | 96% (65/68)   |
| Project.java       | 100% (66/66)  | 100% (29/29)      | 100% (29/29)  |
| ProjectSize.java   | 86% (6/7)     | 100% (1/1)        | 100% (1/1)    |
| Qualification.java | 100% (29/29)  | 100% (16/16)      | 100% (16/16)  |
| Worker.java        | 100% (58/58)  | 100% (34/34)      | 100% (34/34)  |

Our mutation coverage and strength was pretty good for our Classes except for Company which has 96% Mutation Coverage. Inspecting the pit report for Company it looks like we missed coverage for unassign and createWorker. In unassign we missed coverage over the conditional branches:

		if (worker.getProjects().isEmpty()) {
			this.assigned.remove(worker);
		}

		if (worker.isAvailable()) {
			this.available.add(worker);
		} else {
			this.available.remove(worker);
		}

Creating a test (test_worker_with_multiple_projects_stays_assigned_unassign) to validate a worker with non empty projects is not removed from the assigned set improved mutation coverage for the first conditional. Having a test (test_unavailable_worker_updates_available_set_unassign) to validate a worker is added or removed from available workers based on their availability improved mutation coverage for the second conditional.

To kill the createWorker mutation the test test_zeroSalary_createWorker() was added to handle the mutation of this conditional:

if (salary < 0 || Double.isNaN(salary)) {
			return null;
}

Having a test that asserted a salary being 0 valid killed this mutation.

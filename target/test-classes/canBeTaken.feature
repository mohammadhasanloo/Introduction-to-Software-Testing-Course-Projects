Feature: can be taken
  Scenario: course can be taken
    Given student reference that has passed the prerequisite course
    And the course
    When I ask can the student can take the course
    Then I should be told no violation "0"

  Scenario: course cannot be taken
    Given student reference that has not passed the prerequisite course
    And the course
    When I ask can the student can take the course
    Then I should be told one violation "1"
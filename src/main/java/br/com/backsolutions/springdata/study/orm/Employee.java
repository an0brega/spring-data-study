    package br.com.backsolutions.springdata.study.orm;

    import jakarta.persistence.*;

    import java.time.LocalDate;

    @Entity
    @Table(name = "EMPLOYEES")
    public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String employeeName;
        private long cpf;
        private double salary;
        private LocalDate entryDate;

        @ManyToOne
        @JoinColumn(name = "role_id")
        private Role role;

        @ManyToOne
        @JoinColumn(name = "work_unit_id")
        private WorkUnit workUnit;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public long getCpf() {
            return cpf;
        }

        public void setCpf(long cpf) {
            this.cpf = cpf;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public LocalDate getEntryDate() {
            return entryDate;
        }

        public void setEntryDate(LocalDate entryDate) {
            this.entryDate = entryDate;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public WorkUnit getWorkUnit() {
            return workUnit;
        }

        public void setWorkUnit(WorkUnit workUnit) {
            this.workUnit = workUnit;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", employeeName='" + employeeName + '\'' +
                    ", cpf=" + cpf +
                    ", salary=" + salary +
                    ", entryDate=" + entryDate +
                    ", role=" + role +
                    ", workUnit=" + workUnit +
                    '}';
        }
    }
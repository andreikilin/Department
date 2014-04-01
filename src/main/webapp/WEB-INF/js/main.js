var PageController = Class.extend({
    init: function() {
        var self = this;
        var departments = new DepartmentForm();
        var employees = new EmployeeForm();
        $('body')
            .append($('<h2/>').html("Department management"))
            .append($('<div id="actions"/>'));

        var actions = $('#actions');
        actions.on('click', 'button.viewDepartments', function() {
            self.departmentsTable(departments);
        });
        actions.on('click', 'button.viewAddDepartment', function (){
            self.addDepartment(departments);
        });
        actions.on('click', 'button.viewAddEmployee', function() {
            self.addEmployee(employees);
        });

        $(actions)
            .append($('<button class="viewDepartments"/>').html("List departments"))
            .append($('<button class="viewAddDepartment"/>').html("Add department"))
            .append($('<button class="viewAddEmployee"/>').html("Add employee"));

    },
    departmentsTable: function(departments) {
        departments.putDepartments();

    },
    addDepartment: function(departments) {
        departments.newDepartment();
    },

    addEmployee: function(employees){
        employees.addEmployeeForm();
    }

});

$(document).ready(function() {
    var pageController = new PageController();

});
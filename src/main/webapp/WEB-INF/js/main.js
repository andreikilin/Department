var PageController = Class.extend({
    init: function() {
        var self = this;
        var departments = new DepartmentForm();
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

        $(actions)
            .append($('<button class="viewDepartments"/>').html("List departments"))
            .append($('<button class="viewAddDepartment"/>').html("Add department"));

    },
    departmentsTable: function(departments) {
        departments.putDepartments();

    },
    addDepartment: function(departments) {
        departments.newDepartment();
    }

});

$(document).ready(function() {
    var pageController = new PageController();

});
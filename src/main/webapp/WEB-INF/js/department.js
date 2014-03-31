var DepartmentForm = Form.extend({
    init: function() {
        this.table = this.createTable();

    },
    createTable: function() {
        var table = $('<table id="departmentTable" border="1" align="center"/>');
        table.on('click', 'button.listEmployees', function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            var self = tr.data('context').__this;
            self.employeesTable(department);
        });
        table.on('click', 'button.deleteDepartment', function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            var self = tr.data('context').__this;
            self.removeDepartment(department);
        });
        table.on('click', 'button.editDepartment', function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            var self = tr.data('context').__this;
            self.editDepartment(department);
        })
        return table;
    },

    putDepartments: function () {
        this.table.children().remove();
        $.ajax({
            url: "/department/getList",
            context: this
        }).done(function (departments) {
                for (var i = 0; i < departments.length; i++) {
                    var tr = $('<tr/>')
                        .data('context', {department: departments[i], __this: this})
                        .append($('<td/>').html(departments[i].name))
                        .append($('<td/>')
                            .append($('<a href="/department/' + departments[i].id + '/add"/>')
                                .append($('<button/>').html('Add'))))
                        .append($('<td/>')
                            .append($('<button class="editDepartment"/>').html('Edit')))
                        .append($('<td/>')
                            .append($('<button class="listEmployees"/>').html('Employees')))
                        .append($('<td/>')
                            .append($('<button class="deleteDepartment"/>').html('Delete')));
                    this.table.append(tr);
                }
                this.table.appendTo('body');
            });
    },

    employeesTable: function(department) {
        //Thinking about "this"

        $('#listEmployeesIsEmpty').remove();
        $('#employeesTable').remove();

        var table = $('<table id="employeesTable" border="1" align="center"/>').appendTo('body');

        table.on('click', 'button#close', function() {
            table.remove();
        });

        $.ajax( {
            type: "GET",
            url: "/department/getListByDepartment",
            data: {departmentId: department.id},
            context: this,
            dataType: 'json'
        }).done(function (data) {
            if(data.length != 0) {
                table.append($('<tr/>')
                    .append($('<td/>').html('First Name'))
                    .append($('<td/>').html('Last Name'))
                    .append($('<td/>').html('INN'))
                    .append($('<td/>').html('Email'))
                    .append($('<td/>').html('Birthday'))
                    .append($('<td/>').html('Department'))
                    .append($('<td/>').html('Action')));
                for (var i = 0; i < data.length; i++) {
                    var tr = $('<tr/>').data('department', data[i]);

                    tr.append($('<td/>').text(tr.data('department').firstName))
                        .append($('<td/>').html(data[i].lastName))
                        .append($('<td/>').html(data[i].inn))
                        .append($('<td/>').html(data[i].email))
                        .append($('<td/>').html(data[i].day + '-' + data[i].month + '-' + data[i].year))
                        .append($('<td/>').html(data[i].department.name))
                        .append($('<td/>')
                            .append($('<a href="/employee/' + data[i].id + '/edit"/>')
                                .append($('<button/>')
                                    .html('Edit')))
                            .append($('<a href="/employee/' + data[i].id + '/delete"/>')
                                .append($('<button/>')
                                    .html('Delete')))
                        );
                    table.append(tr);
                }
                table
                    .append($('<button id="close"/>').html('Close'));
            }else {
                $('<h4 align="center" id="listEmployeesIsEmpty"/>').html("No employees in \""+ department.name + "\"")
                    .appendTo('body');
            }

        });
    },

    newDepartment: function () {
        var dialog = $('<div class="addDepartmentDialog"/> ');
        dialog.empty();
        var self = this;
        dialog
            .append($('<form id="addDepartment"/>')
            .append($('<table/>')
                .append($('<tr/>')
                    .append($('<td/>').html("Department Name"))
                    .append($('<td/>')
                        .append($('<input name="name"/>')))
                    .append($('<input type="submit" class="submit" value="Add"/>')))));
        dialog.appendTo('body');
        $('#addDepartment').validate({
//            context:this,
//            onkeyup: false,
            rules: {
                name: {
                    required: true,
                    minlength: 3,
                    maxlength: 16,
                    remote: {
                        url: "/department.do",
                        type: "POST"
                    }
                }
            },
            messages: {
                name: {
                    required: "Field is required",
                    minlength: "Length must be more 3 and less 16 characters",
                    maxlength: "Length must be more 3 and less 16 characters",
                    remote: "This name is already taken."
                }

            },
            submitHandler: function () {
                $.ajax({
                    type: "POST",
                    url: "/department/new",
//                    context: this,
                    data: {
                        name: $('input[name="name"]').val()
                    },
                    success: function (response) {
                        if (response.length != 0) {
                            var message = "";
                            for (var i = 0; i < response.length; i++) {
                                message += response[i] + '\n';
                            }
                            alert(message);
                        } else {
//                            alert("Success");
                            dialog.empty();
                            self.putDepartments();
                        }
                    }
                });
            }
        })
    },

    removeDepartment: function(department) {
        $('depIsNotEmpty').remove();
        $.ajax( {
            type: "GET",
            url: "/department/getListByDepartment",
            data: {departmentId: department.id},
            context: this,
            dataType: 'json'
        }).done(function (data) {
            if(data.length != 0) {
                $('<h4 align="center" id="depIsNotEmpty"/>').html("\""+ department.name + "\" is not empty")
                    .appendTo('body');
            } else {
                $.ajax( {
                    type: "POST",
                    url: "/department/delete",
                    context:this,
                    data: {departmentId: department.id}
                }).done(function(response) {
//                    alert(response);
                    this.putDepartments();
                });
            }
        });
    },

    editDepartment: function(department) {
        var dialog = $('<div class="editDepartmentDialog"/> ');
        dialog.empty();
        var self = this;
        dialog
            .append($('<form id="editDepartment"/>')
                .append($('<table/>')
                    .append($('<tr/>')
                        .append($('<td/>').html("Department Name"))
                        .append($('<td/>')
                            .append($('<input name="name"/>'))
                            .append($('<input hidden="true" name="id"/>')))
                        .append($('<input type="submit" class="submit" value="Save"/>')))));
        dialog.appendTo('body');
        $('input[name="id"]').attr("value", department.id);
        $('input[name="name"]').attr("value", department.name);

        $('#editDepartment').validate({
            rules: {
                name: {
                    required: true,
                    minlength: 3,
                    maxlength: 16,
                    remote: {
                        url: "/department.do",
                        type: "POST",
                        data: {
                            id: function() {
                                return $('input[name="id"]').val();
                            },
                            name: function() {
                                return  $('input[name="name"]').val();
                            }
                        }
                    }
                }
            },
            messages: {
                name: {
                    required: "Field is required",
                    minlength: "Length must be more 3 and less 16 characters",
                    maxlength: "Length must be more 3 and less 16 characters",
                    remote: "This name is already taken."
                }

            },
            submitHandler: function () {
                $.ajax({
                    type: "POST",
                    url: "/department/edit",
                    data: {
                        id: $('input[name="id"]').val(),
                        name: $('input[name="name"]').val()
                    },
                    success: function (response) {
                        if (response.length != 0) {
                            var message = "";
                            for (var i = 0; i < response.length; i++) {
                                message += response[i] + '\n';
                            }
                            alert(message);
                        } else {
                            alert("Success");
                            dialog.empty();
                            self.putDepartments();
                        }
                    }
                });
            }
        })
    }
});

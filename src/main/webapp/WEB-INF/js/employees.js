var EmployeeForm = Form.extend({
    init: function() {
        this.table = this.__createTable();
        this.form = this.__createForm();
        $.validator.addMethod("valueNotEquals", function(value, element, arg){
            return arg != value;
        }, "Value must not equal arg.");
        this.form.validate({
            debug: true,
            context: this,
            rules: {
                firstName: {
                    required: true,
                    minlength: 3,
                    maxlength: 16
                },
                lastName: {
                    required: true,
                    minlength: 3,
                    maxlength: 16
                },
                email: {
                    required: true,
                    email: true,
                    remote: {
                        url: "/email.do",
                        type: "POST",
                        data: {
                            id: function() {
                                return $('input[name="id"]').val();
                            },
                            email: function() {
                                return $('input[name="email"]').val();
                            }
                        },
                        complete: function(data){
                            console.log(data)
                        }
                    }
                },
                inn: {
                    required: true,
                    number: true,
                    minlength: 6,
                    maxlength: 6,
                    remote: {
                        url: "/inn.do",
                        type: "POST",
                        data: {
                            id: function() {
                                return $('input[name="id"]').val();
                            },
                            inn: function() {
                                return $('input[name="inn"]').val();
                            }
                        }
                    }
                },
                day: {
                    valueNotEquals: "0"
                },
                month: {
                    valueNotEquals: "0"
                },
                year: {
                    valueNotEquals: "0"
                },
                department: {
                    valueNotEquals: "0"
                }
            },
            messages: {
                firstName: {
                    required: "Field is required",
                    length: "Length must be more 3 and less 16 characters"
                },
                lastName: {
                    required: "Field is required",
                    length: "Length must be more 3 and less 16 characters"
                },
                email: {
                    required: "Field is required",
                    remote: "This email already is taken"
                },
                inn: {
                    required: "Field is required",
                    length: "Length must be 6 characters",
                    number: "Inn must be numerical",
                    remote: "Employee with this inn already exist"
                },
                day: {
                    valueNotEquals: "Choose day"
                },
                month: {
                    valueNotEquals: "Choose month"
                },
                year: {
                    valueNotEquals: "Choose year"
                },
                department: {
                    valueNotEquals: "Choose department"
                }
            },
            submitHandler: $.proxy(function (form) {
                this.__sendData();
            }, this)
        })
    },

    __createTable: function() {
        var table = $('<table id="employeesTable" align="center"/>');
        table.on('click', 'button#close', function() {
            table.remove();
        });
        table.on('click', 'button.editEmployee', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var employee = tr.data('context').employee;
            this.editEmployee(employee);
        }, this));
        table.on('click', 'button.deleteEmployee', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var employee = tr.data('context').employee;
            this.removeEmployee(employee);
        }, this));
        return table;
    },

    __createForm: function () {
        var form = $('<form id="editEmployee" />');
        form.on('click', 'button.close', function () {
            form.detach();
        });
        return form;
    },

    __putForm: function() {
        this.form.empty();
        this.form
            .append($('<table/>')
                .append($('<tr/>')
                    .append($('<td/>').html('First Name'))
                    .append($('<td/>')
                        .append($('<input name="firstName" type="text"/>'))
                        .append($('<input hidden="true" name="id"/>'))))
                .append($('<tr/>')
                    .append($('<td/>').html('Last Name'))
                    .append($('<td/>')
                        .append($('<input name="lastName" type="text"/>'))))
                .append($('<tr/>')
                    .append($('<td/>').html('Email'))
                    .append($('<td/>')
                        .append($('<input name="email" type="text"/>'))))
                .append($('<tr/>')
                    .append($('<td/>').html('Inn'))
                    .append($('<td/>')
                        .append($('<input name="inn" type="text" value=""/>'))))
                .append($('<tr/>')
                    .append($('<td/>').html('Birthday'))
                    .append($('<td/>')
                        .append($('<select name="day" />')
                            .append($(this.selectDay()))))
                    .append($('<td/>')
                        .append($('<select name="month"/>')
                            .append($(this.selectMonth()))))
                    .append($('<td/>')
                        .append($('<select name="year"/>')
                            .append($(this.selectYear())))))
                .append($('<tr/>')
                    .append($('<td/>').html('Department'))
                    .append($('<td/>')
                        .append($('<select id="departmentId" name="department"/>')
                            .append($(this.selectDepartments())))))
                .append($('<tr/>')
                    .append($('<td/>')
                        .append($('<input type="submit" class="submit" value="Save"/>'))
                        .append($('<button class="close"/>').html("Cancel")))));
    },

    __putTable: function(data) {
        this.table.children().remove();
//        this.table.detach();
                if(data.length != 0) {
                this.table.attr("border", "1")
                    .append($('<tr/>')
                    .append($('<td/>').html('First Name'))
                    .append($('<td/>').html('Last Name'))
                    .append($('<td/>').html('INN'))
                    .append($('<td/>').html('Email'))
                    .append($('<td/>').html('Birthday'))
                    .append($('<td/>').html('Department'))
                    .append($('<td/>').html('Action')));
                for (var i = 0; i < data.length; i++) {
                    var tr = $('<tr/>').data('context', {employee: data[i]});

                    tr
                        .append($('<td/>').html(data[i].firstName))
                        .append($('<td/>').html(data[i].lastName))
                        .append($('<td/>').html(data[i].inn))
                        .append($('<td/>').html(data[i].email))
                        .append($('<td/>').html(data[i].day + '-' + data[i].month + '-' + data[i].year))
                        .append($('<td/>').html(data[i].department.name))
                        .append($('<td/>')
                            .append($('<button class="editEmployee"/>').html('Edit'))
                            .append($('<button class="deleteEmployee"/>').html('Delete')));

                    this.table.append(tr);
                }
                this.table
                    .append($('<button id="close"/>').html('Close'));
            }else {
                this.table
                    .append($('<tr/>')
                        .append($('<td/>')
                            .append($('<h4/>').html("No employees in \"" + department.name + "\""))));

            }

    },

    __refreshEmployeeForm: function() {
        this.form.detach();
    },

    __sendData: function () {
        var url = "";
        switch(this.action) {
            case "add":
                url = "/employee/new";
                break;
            case "edit":
                url = "/employee/edit";
                break;
            default :
                break;
        }
        $.ajax({
            type: "POST",
            url: url,
            context:this,
            data: {
                id: $('input[name="id"]').val(),
                firstName: $('input[name="firstName"]').val(),
                lastName: $('input[name="lastName"]').val(),
                email: $('input[name="email"]').val(),
                inn: $('input[name="inn"]').val(),
                day: $('select[name="day"]').val(),
                month: $('select[name="month"]').val(),
                year: $('select[name="year"]').val(),
                department: $('select[id="departmentId"]').val()
            },
            success: $.proxy(function(response) {
                console.log(response);
//                alert("submit successful");
                this.__refreshEmployeeForm();
                if(this.action == "edit") {

                }
                this.action = "";
            }, this),
            error: function () {
                alert("error");
            }
        });
    },

    __setTestData: function() {
//        $('input[name="id"]').attr("value", "13");
        $('input[name="firstName"]').attr("value", "Nikita");
        $('input[name="lastName"]').attr("value", "agent");
        $('input[name="email"]').attr("value", "nikita@cia.com");
        $('input[name="inn"]').attr("value", "324623");
        $('select[name="day"] :eq(26)').attr("selected", "selected");
        $('select[name="month"] :eq(3)').attr("selected", "selected");
        $('select[name="year"] :contains("1972")').attr("selected", "selected");
        $('select[id="departmentId"] :eq(34)').attr("selected", "selected");
    },

    listEmployeesByDepartment: function(department) {
        $.ajax( {
            type: "GET",
            url: "/department/getListByDepartment",
            data: {departmentId: department.id},
            context: this,
            dataType: 'json'
        }).done(function(data) {
            this.__putTable(data);
            return;
        });
        this.table.appendTo('body');
    },

    listEmployees: function() {
        $.ajax( {
            type: "GET",
            url: "/employee/getList",
            context: this,
            dataType: 'json'
        }).done(function(data) {
            this.__putTable(data);
        });
        this.table.appendTo('body');
    },

    addEmployee: function() {
        this.__putForm();
        this.form.appendTo('body');
        this.action = "add";
    },

    editEmployee: function(employee) {
        this.__putForm();
        this.form.appendTo('body');
        this.action = "edit";
        $('input[name="id"]').attr("value", employee.id);
        $('input[name="firstName"]').attr("value", employee.firstName);
        $('input[name="lastName"]').attr("value", employee.lastName);
        $('input[name="email"]').attr("value", employee.email);
        $('input[name="inn"]').attr("value", employee.inn);
        $('select[name="day"] :eq('+employee.day+')').attr("selected", "selected");
        $('select[name="month"] :eq('+employee.month+')').attr("selected", "selected");
        $('select[name="year"] :contains('+employee.year+')').attr("selected", "selected");
        $('select[id="departmentId"] :eq(' +employee.department.id+ ')').attr("selected", "selected");
    },

    removeEmployee: function(employee) {

    }


});





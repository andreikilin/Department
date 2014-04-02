var DepartmentForm = Form.extend({
    init: function() {
        this.employees = new EmployeeForm();
        this.table = this.__createTable();
        this.form = this.__createForm();
        this.form.validate({
            context: this,
//            onkeyup: false,
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
                    length: "Length must be more 3 and less 16 characters",
                    remote: "This name is already taken."
                }

            },
            submitHandler: $.proxy(function() {
                this.__saveData();
                this.__refreshForm();
            },this)
        });

    },
    __createTable: function() {
        var table = $('<table id="departmentTable" border="1" align="center"/>');
        table.on('click', 'button.listEmployees', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            this.employees.listEmployeesByDepartment(department);
        }, this));
        table.on('click', 'button.deleteDepartment', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            this.removeDepartment(department);
        }, this));
        table.on('click', 'button.editDepartment', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            this.editDepartment(department);
        }, this));
        return table;
    },

    __putDepartments: function () {
        this.table.children().remove();
        $.ajax({
            url: "/department/getList",
            context: this
        }).done(function (departments) {
                for (var i = 0; i < departments.length; i++) {
                    var tr = $('<tr/>')
                        .data('context', {department: departments[i]})
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

    __createForm: function () {
        var form = $('<form id="addDepartment"/>');
        form.on('click', 'button.close', function() {
            form.detach();
        });
        form.empty();
        form
            .append($('<table/>')
                .append($('<tr/>')
                    .append($('<td/>').html("Department Name"))
                    .append($('<td/>')
                        .append($('<input name="name"/>'))
                        .append($('<input hidden="true" name="id"/>')))
                    .append($('<input type="submit" class="submit" value="Save"/>'))
                    .append($('<button class="close"/>').html("Cancel"))));
        return form;
    },

    __refreshForm: function() {
        this.form.detach();
    },

    __saveData: function() {
        var url = "";
        switch(this.action) {
            case "add":
                url = "/department/new";
                break;
            case "edit":
                url = "/department/edit";
                break;
            default :
                break;
        }
        $.ajax({
            type: "POST",
            url: url,
            context: this,
            data: {
                id: $('input[name="id"]').val(),
                name: $('input[name="name"]').val()
            },
            success: $.proxy(function (response) {
                if (response.length != 0) {
                    var message = "";
                    for (var i = 0; i < response.length; i++) {
                        message += response[i] + '\n';
                    }
                    alert(message);
                } else {
//                            alert("Success");
//                    this.form.empty();
                    this.action="";
                    this.__putDepartments();
                }
            }, this)
        });
    },

    showDepartments: function() {
        this.__putDepartments();
    },

    addDepartment: function() {
        this.form.appendTo('body');
        this.action = "add";
    },

    editDepartment: function(department) {
        this.form.appendTo('body');
        this.action = "edit";
        $('input[name="id"]').attr("value", department.id);
        $('input[name="name"]').attr("value", department.name);

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
                    this.__putDepartments();
                });
            }
        });
    }

});

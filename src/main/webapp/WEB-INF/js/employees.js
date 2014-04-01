var EmployeeForm = Form.extend({
    init: function() {
        this.form = this.createForm();
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
            submitHandler: function (form) {
                $.ajax({
                    type: "POST",
                    url: "/employee/new",
                    data: {
                        id: $('input[name="id"]').val(),
                        firstName: $('input[name="firstName"]').val(),
                        lastName: $('input[name="lastName"]').val(),
                        email: $('input[name="email"]').val(),
                        inn: $('input[name="inn"]').val(),
                        day: $('select[name="day"]').val(),
                        month: $('select[name="month"]').val(),
                        year: $('select[name="year"]').val(),
                        departmentId: $('select[id="departmentId"]').val()
                    },
                    success: function(response) {
                        console.log(response);
                        alert("submit successful");
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
        })
    },

    createForm: function() {
        var form = $('<form id="editEmployee" />')
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
                        .append($('<input type="submit" class="submit" value="Save"/>')))))

        //.appendTo($('body'));
        return form;
    },

    addEmployeeForm: function() {
        this.form.appendTo('body');
        this.setTestData();
    },

    removeEmployee: function() {

    },

    editEmployee: function() {

    },


    sendData: function () {
        $.ajax({
            type: "POST",
            url: "/employee/new",
            data: {
                firstName: $('input[name="firstName"]').val(),
                lastName: $('input[name="lastName"]').val(),
                email: $('input[name="email"]').val(),
                inn: $('input[name="inn"]').val(),
                day: $('select[name="day"]').val(),
                month: $('select[name="month"]').val(),
                year: $('select[name="year"]').val(),
                departmentId: $('select[id="departmentId"]').val()
            },
            success: function(response) {
                console.log(response);
                alert("submit successful");
            },
            error: function () {
                alert("error");
            }
        });
    },
    setTestData: function() {
        $('input[name="id"]').attr("value", "13");
        $('input[name="firstName"]').attr("value", "Poruchik");
        $('input[name="lastName"]').attr("value", "Golitsin");
        $('input[name="email"]').attr("value", "oruchikgolitsin@yandex.ru");
        $('input[name="inn"]').attr("value", "254762");
        $('select[name="day"] :eq(14)').attr("selected", "selected");
        $('select[name="month"] :eq(2)').attr("selected", "selected");
        $('select[name="year"] :contains("1956")').attr("selected", "selected");
        $('select[id="departmentId"] :eq(3)').attr("selected", "selected");
    }
});





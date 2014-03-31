var Form = Class.extend({
    selectDepartments: function () {
        var list = [];
        $.ajax({
            url: "/department/getList",
            async: false,
            success: function (data) {
                list = data;
            }
        });
        var select = "<option value='0'>Select</option>";
        $.each(list, function (key, value) {
            select += "<option value='" + value["id"] + "'>" + value["name"] + "</option>";
        });
        return select;
    },
    selectDay: function() {
    var list = [];
        $.ajax({
            url: "/getDayList",
            async: false,
            success: function (data) {
                list = data;
            }
        });
        var select = "<option value='0'>Select</option>";
        $.each(list, function(key, value) {
            select += "<option value='" + value + "'>" + value + "</option>"
        });
        return select;
    },
    selectMonth: function () {
        var map = [];
        $.ajax({
            url: "/getMonthMap",
            async: false,
            success: function (data) {
                map = data;
            }
        });
        var select = "<option value='0'>Select</option>";
        $.each(map, function(key, value) {
            select += "<option value='" + key + "'>" + value + "</option>"
        });
        return select;
    },
    selectYear: function () {

            var list = [];
            $.ajax({
                url: "/getYearList",
                async: false,
                success: function (data) {
                    list = data;
                }
            });
            var select = "<option value='0'>Select</option>";
            $.each(list, function (key, value) {
                select += "<option value='" + value + " '>" + value + "</option>"
            })

            return select;
        }
});

var EmployeeForm = Form.extend({
    createForm: function() {
        $('<form id="editEmployee" />')
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
                            .append($(Days))))
                    .append($('<td/>')
                        .append($('<select name="month"/>')
                            .append($(this.selectMonth())
                            )))
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
                        .append($('<button/>')
                            //.on('click',this.sendData())
                            .html("Save"))))
            )
            .appendTo($('body'));
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
        //    $('input[name="id"]').attr("value", "0");
        $('input[name="firstName"]').attr("value", "Poruchik");
        $('input[name="lastName"]').attr("value", "Golitsin");
        $('input[name="email"]').attr("value", "poruchikgolitsin@yandex.ru");
        $('input[name="inn"]').attr("value", "254762");
        $('select[name="day"] :eq(14)').attr("selected", "selected");
        $('select[name="month"] :eq(2)').attr("selected", "selected");
        $('select[name="year"] :contains("1956")').attr("selected", "selected");
        $('select[id="departmentId"] :eq(3)').attr("selected", "selected");
    }
});



//$(document).ready(
//    var trololo = (function() {
//    var form = new EmployeeForm();
//    form.createForm();
//    form.setTestData();
////    $('#editEmployee').validate({
////        rules: {
////            firstName: {
////                required: true,
////                minLength: 3,
////                maxLength: 16
////            },
////            lastName: {
////                required: true,
////                minLength: 3,
////                maxLength: 16
////            },
////            email: {
////                required: true,
////                minLength: 6,
////                maxLength: 20
////            },
////            inn: {
////                required: true,
////                minLength: 6,
////                maxLength: 6
////            }
////        },
////        messages: {
////            firstName: {
////                required: "Field is required",
////                minLength: "Length must be more 3 and less 16 characters",
////                maxLength: "Length must be more 3 and less 16 characters"
////            },
////            lastName: {
////                required: "Field is required",
////                minLength: "Length must be more 3 and less 16 characters",
////                maxLength: "Length must be more 3 and less 16 characters"
////            },
////            email: {
////                required: "Field is required",
////                minLength: "Length must be more 3 and less 20 characters",
////                maxLength: "Length must be more 3 and less 20 characters"
////            },
////            inn: {
////                required: "Field is required",
////                minLength: "Length must be 6 characters",
////                maxLength: "Length must be 6 characters"
////            },
////            submitHandler: function(form) {
////                form.submit();
////            }
////        }
////    })
//
//});



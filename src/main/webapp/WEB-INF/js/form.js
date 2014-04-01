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

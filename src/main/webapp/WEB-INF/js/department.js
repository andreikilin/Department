var employeeMenu = new Menu({
    elem: $('#food-menu')
});

employeeMenu.close();


$(document).ready(function () {
    $('#departmentTable').append('<table border="1" align="center"/>');
    $.getJSON("/department/getList", function (departments) {

        var table = $('#departmentTable').children();
        for (var i = 0; i < departments.length; i++) {
            var tr = $('<tr/>')
                .append($('<td/>').html(departments[i].name))
                .append($('<td/>').html('<a href="/department/' + departments[i].id + '/add"><button type="button">Add</button>'))
                .append($('<td/>').html('<a href="/department/' + departments[i].id + '/edit"><button type="button">Edit</button>'))
                .append($('<td/>').html('<button type="button" onclick="listByDepartment(' + departments[i].id + ')">Employees</button>'))
                .append($('<td/>').html('<a href="/department/' + departments[i].id + '/delete"><button type="button">Delete</button>'));

            table.append(tr)
                .append($('<tr/>')
                    .html('<div id="employeeMenu"></div>'));
        }

    });
});

function listByDepartment(departmentId) {
    $.ajax({
        type: "GET",
        url: "/department/getListByDepartment/",
        data: {departmentId: departmentId}
        }).done(function(data) {
        var elem = $('#dep');
        var table = $('<table border="1"/>');
        table.append($('<tr/>')
            .append($('<td/>').html('First Name'))
            .append($('<td/>').html('Last Name'))
            .append($('<td/>').html('INN'))
            .append($('<td/>').html('Email'))
            .append($('<td/>').html('Birthday'))
            .append($('<td/>').html('Department'))
            .append($('<td/>').html('Action')))
        for (var i = 0; i < data.length; i++) {
            var tr = $('<tr/>')
                .append($('<td/>').html(data[i].firstName))
                .append($('<td/>').html(data[i].lastName))
                .append($('<td/>').html(data[i].inn))
                .append($('<td/>').html(data[i].email))
                .append($('<td/>').html(data[i].day + '-' + data[i].month + '-' + data[i].year))
                .append($('<td/>').html(data[i].department.name))
                .append($('<td/>').html('<a href="/employee/' + data[i].id + '/edit"><button type="button">Edit</button></a>&nbsp;'
                                        + '<a href="/employee/' + data[i].id + '/delete"><button type="button">Delete</button></a>'));

            table.append(tr);
        }
        elem.append(table);

    })
}
function Menu(options) {
    var self = this;
    var elem = options.elem;

    elem.on('mousedown selectstart', false);
    elem.on('click', '.menu-title', onTitleClick);

    function onTitleClick() {
        if (elem.hasClass('menu-open')) {
            self.close();
        } else {
            self.open();
        }
    }

    this.open = function() {
        elem.addClass('menu-open');
    };

    this.close = function() {
        elem.removeClass('menu-open');
    };

}

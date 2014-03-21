// Controller
PageController = function () {
    this.table = this.createTable();
};

//Method in prototype
PageController.prototype.createTable = function () {
    var table = $('<table id="listEmployees" border="1"/>').appendTo($('#Employees'));

//    table.on('click', 'button.delete', function(e) {
//        var button = $(e.target);
//
//        var tr = button.parents('tr');
//
//        var department = tr.data('department');
//
//
//    });
//    table.on('click', 'button.delete', function(e) {
//        var button = $(e.target);
//
//        var tr = button.parents('tr');
//
//        var department = tr.data('department');
//
//
//    });
//
    return table;
};

PageController.prototype.refreshTable = function (departmentId) {
    $('tr:gt(1)', this.table).detach();

    $.ajax({
        type: "GET",
        url: "/department/getListByDepartment",
        data: {departmentId: departmentId},
        context: this,
        dataType: 'json'
    }).done(function (data) {
        this.table.append($('<tr/>')
            .append($('<td/>').html('First Name'))
            .append($('<td/>').html('Last Name'))
            .append($('<td/>').html('INN'))
            .append($('<td/>').html('Email'))
            .append($('<td/>').html('Birthday'))
            .append($('<td/>').html('Department'))
            .append($('<td/>').html('Action')));
        for (var i = 0; i < data.length; i++) {
            var tr = $('<tr/>')
                .data('department', data[i])
                .append($('<td/>').html(data[i].firstName))
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
            this.table.append(tr);
        }

    });
};

PageController.hideTable = function () {
    this.table.hide();
};

PageController,showTable = function() {
    this.table.show;
};

PageController.showAddDialog = function () {
    this.hideTable();

    this.dialog = $('#Employees');
};

PageController.closeDialog = function () {
    if (this.dialog) {
        this.dialog.detach();
    }

    this.showTable();
    this.refreshTable();
};


$(document).ready(function () {
    var pageController = new PageController();

    $('#departmentTable').append('<table border="1" align="center"/>');
    $.getJSON("/department/getList", function (departments) {

        var table = $('#departmentTable').children();
        for (var i = 0; i < departments.length; i++) {
            var onClick = (function(departmentId) {
                return function() {
                    pageController.refreshTable(departmentId);
                }
            }
                )(departments[i].id);
            var tr = $('<tr/>')
                .append($('<td/>').html(departments[i].name))
                .append($('<td/>')
                    .append($('<a href="/department/' + departments[i].id + '/add"/>')
                        .append($('<button type="button"/>')
                            .html('Add'))))
                .append($('<td/>')
                    .append($('<a href="/department/' + departments[i].id + '/edit"/>')
                        .append($('<button type="button"/>')
                            .html('Edit'))))
                .append($('<td/>')
                    .append($('<button/>')
                        .html('Employees')
                            .on('click', onClick)))
                .append($('<td/>')
                    .append($('<a href="/department/' + departments[i].id + '/delete"/>')
                        .append($('<button type="button"/>')
                            .html('Delete'))))

            table.append(tr);
        }

    });
});



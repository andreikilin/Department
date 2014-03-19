$.getJSON("/employee/getList", function (employees) {

    var items = [];
    for (var i = 0; i < employees.length; i++) {
        items.push('<td>' + employees[i].firstName + '</td>'
            + '<td>' + employees[i].lastName + '</td>'
            + '<td>' + employees[i].inn + '</td>'
            + '<td>' + employees[i].email + '</td>'
            + '<td>' + employees[i].day + '-' + employees[i].month + '-' + employees[i].year +'</td>'
            + '<td>' + employees[i].department.name + '</td>'
            + '<td>'+ '<a href="/employee/' + employees[i].id + '/edit"><button type="button">Edit</button></a>&nbsp;'
                    + '<a href="/employee/' + employees[i].id + '/delete"><button type="button">Delete</button></a>' +'</td>'
        );
    }

    var table = document.createElement('table');
    table.setAttribute('id', 'employeeTable');
    table.setAttribute('border', '1');
    table.setAttribute('align', 'center');
    var tbody = document.createElement('tbody');
    var tr = document.createElement('tr');
    tr.innerHTML = '<td>Name</td><td>Surname</td><td>Inn</td><td>Email</td><td>Birthday</td><td>Department</td><td>Action</td>';
    tbody.appendChild(tr);
    for (var c = 0; c < employees.length; c++) {
        tr = document.createElement('tr');
        tr.innerHTML = items[c];
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    document.getElementById('employeeTable').appendChild(table);


})

$(function () {
    list();

    $('#btnAdd').on('click', function () {
        var code = $("#txtCode").val();
        var name = $("#txtName").val();

        if (code === "" || name === '') {
            alert("Code and name fields required!");
        } else {
            var jsonClient = {
                "code": code,
                "name": name
            };

            $.ajax({
                type: "POST",
                url: "http://localhost:8099/v1/clients",
                contentType: "application/json",
                data: JSON.stringify(jsonClient),
                success: function(result){
                    list();
                }
            });
        }
    });

    $('#btnUpdate').on('click', function () {
        var code = $("#txtCode").val();
        var name = $("#txtName").val();

        var jsonClient = {
            "code": code,
            "name": name
        };

        $('#tblCustomers tbody tr').eq($('#hfRowIndex').val()).find('td').eq(1).html(name);

        $.ajax({
            type: "PUT",
            url: "http://localhost:8099/v1/clients",
            contentType: "application/json",
            data: JSON.stringify(jsonClient),
            success: function(result){
                $('#btnAdd').show();
                $('#btnUpdate').hide();
                clearFields();
                list();
            }
        });
    });

    $("#tblCustomers").on("click", ".delete", function (e) {
        if (confirm("Are you sure want to delete this record!")) {
            var row = $(this).closest('tr');
            $('#hfRowIndex').val($(row).index());
            var td = $(row).find("td");

            $.ajax({
                type: "DELETE",
                url: "http://localhost:8099/v1/clients/" + $(td).eq(0).html(),
                contentType: "application/json",
                success: function(result){
                    list();
                }
            });

        } else {
            e.preventDefault();
        }
    });

    $('#btnSearch').on('click', function () {
        var codeSearch = $("#txtCodeSearch").val();

        $.ajax({
            type: "GET",
            url: "http://localhost:8099/v1/clients/" + codeSearch,
            contentType: "application/json",
            success: function(result){
                $("#tblCustomers tbody").empty();

                var edit = "<a class='edit' href='JavaScript:void(0);' >Edit</a>";
                var del = "<a class='delete' href='JavaScript:void(0);'>Delete</a>";

                var table = "<tr><td>" + result.code + "</td><td>" + result.name + "</td><td>" + edit + "</td><td>" + del + "</td></tr>";
                $("#tblCustomers").append(table);
            }
        });
    });

    $('#btnClear').on('click', function () {
        clearFields();
    });

    $("#tblCustomers").on("click", ".edit", function (e) {
        var row = $(this).closest('tr');
        $('#hfRowIndex').val($(row).index());
        var td = $(row).find("td");
        $('#txtCode').val($(td).eq(0).html());
        $('#txtName').val($(td).eq(1).html());
        $('#btnAdd').hide();
        $('#btnUpdate').show();
    });
});

function clearFields() {
    $("#txtCode").val("");
    $("#txtName").val("");
    $("#hfRowIndex").val("");
}

function list() {
    $("#tblCustomers tbody").empty();

    $.ajax({
        type: "GET",
        url: "http://localhost:8099/v1/clients",
        contentType: "application/json",
        success: function(result){
            for (var client of result) {
                var edit = "<a class='edit' href='JavaScript:void(0);' >Edit</a>";
                var del = "<a class='delete' href='JavaScript:void(0);'>Delete</a>";

                var table = "<tr><td>" + client.code + "</td><td>" + client.name + "</td><td>" + edit + "</td><td>" + del + "</td></tr>";
                $("#tblCustomers").append(table);

                $("#txtCode").val("");
                $("#txtName").val("");
            }
        }
    });
}
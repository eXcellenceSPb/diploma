$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $(".edit").click(function () {
        $("#cid").val($(this).closest("tr").find('.cid').text());
        $("#corganisation").val($(this).closest("tr").find(".corganisation").text());
        $("#cdate").val($(this).closest("tr").find(".cdate").text());
        $("#cfirstname").val($(this).closest("tr").find(".cfirstname").text());
        $("#clastname").val($(this).closest("tr").find(".clastname").text());
        $("#crank").val($(this).closest("tr").find(".crank").text());
        $("#cunit").val($(this).closest("tr").find(".cunit").text());
        $("#cnumber").val($(this).closest("tr").find(".cnumber").text());

    });
});
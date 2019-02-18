$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable2 tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $(".edit").click(function () {
        $("#mid").val($(this).closest("tr").find('.mid').text());
        $("#mwound_time").val($(this).closest("tr").find(".mwound_time").text());
        $("#mantibiotic").val($(this).closest("tr").find(".mantibiotic").text());
        $("#mserum").val($(this).closest("tr").find(".mserum").text());
        $("#manatoxin").val($(this).closest("tr").find(".manatoxin").text());
        $("#mantidot").val($(this).closest("tr").find(".mantidot").text());
        $("#manaesthetic").val($(this).closest("tr").find(".manaesthetic").text());
        $("#mcommit").val($(this).closest("tr").find(".mcommit").text());
        $("#mwound").val($(this).closest("tr").find(".mwound").text());
        $("#mlocation").val($(this).closest("tr").find(".mlocation").text());
        $("#mdiagnosis").val($(this).closest("tr").find(".mdiagnosis").text());
        $("#mevacuation").val($(this).closest("tr").find(".mevacuation").text());
        $("#mplace").val($(this).closest("tr").find(".mplace").text());
        $("#mtransport").val($(this).closest("tr").find(".mtransport").text());
        $("#mqueue").val($(this).closest("tr").find(".mqueue").text());
        $("#minfo").val($(this).closest("tr").find(".minfo").text());

    });
});
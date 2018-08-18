<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Manager</title>
<style>
  button {margin-right: 5px;}
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
function expand()
{
    var li = $(this).parent();
    $(this).remove(); // remove right away so the user cannot click twice
    $.ajax({
        url: "service/files/" + li.attr("data-id"),
        success: function(data){
            var ul = $("<ul></ul>");
            data.forEach(function(file){
                var newli = $("<li data-id='" + file.id + "'></li>");
                if( file.folder )
                {
                    var btn = $("<button>+</button>");
                    btn.click(expand);
                    newli.append(btn);
                }
                newli.append(file.name);
                ul.append(newli);
            });
            li.append(ul);
        }
    })
}
$(function(){
    $("button").click(expand);
});
</script>
</head>
<body>
<ul>
<c:forEach items="${files}" var="file">
  <li data-id="${file.id}">
    <c:if test="${file.folder}"><button>+</button></c:if> ${file.name}
  </li>
</c:forEach>
</ul>
</body>
</html>

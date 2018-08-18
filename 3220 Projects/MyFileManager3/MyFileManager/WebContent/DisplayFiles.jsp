<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<title>
<c:choose>
<c:when test = "${parentFile == null}">
MyFileManager
</c:when>

<c:otherwise>
${parentFile.name}
</c:otherwise>
</c:choose>

</title>
</head>
<body>

<header><h3><a href="MyFileManager">MyFileManager</a></h3></header>

<div class="main">
<div class="content">

<nav>
<div class="file-nav">
<c:if test="${parent == 0}">
<span>FileManager</span>
</c:if>


<c:if test="${parent != 0}">
<c:choose>
<c:when test="${empty ancestors}">
<span><a href="MyFileManager">FileManager </a>&rarr;</span><span>${parentFile.name}</span>
</c:when>

<c:otherwise>
<a href="MyFileManager">FileManager</a>
<c:forEach items="${ancestors}" var="f">
<span><a href="MyFileManager?key=${f.id}">${f.name} </a>&rarr;</span>
</c:forEach>
<span>${parentFile.name}</span>
</c:otherwise>
</c:choose>
</c:if>
</div>
<div class="create">
<a href="CreateFolder?key=${parent}">New Folder </a>|
<a href="Upload?key=${parent}"> Upload File</a>
</div>
</nav>
<table>
<tr>
<th>Name</th>
<th>Date</th>
<th>Size</th>
<th>Operations</th>
</tr>



<c:choose>

<c:when test="${empty parentFile}">
<c:forEach items="${Files}" var="f">
<tr>
<c:if test="${empty f.parent}">


<c:choose>
<c:when test="${not f.folder}">
<c:set var="icon" value="blank"/>
<c:set var="filename" value="${f.name}"/>
<c:set  var="ext" value='${fn:split(fn:replace(filename, ".", "|"), "|")}'/>

<c:forEach items="${imgLib}" var="l">
<c:if test="${ext[1] eq l }">
<c:set var="icon" value="${ext[1] }"/>
</c:if>

</c:forEach>


<td><img src="images/${icon}.ico"/><a href="Download?key=${f.id}&name=${f.name}&type=${f.type}">${f.name}</a></td> 
<td><fmt:formatDate pattern="MM/dd/yyyy hh:mma" value="${f.date}" /></td> 
<td>


<c:choose>
<c:when test="${f.size lt 1024 }">
${f.size}B
</c:when>
<c:otherwise>
${f.size/1024}KB
</c:otherwise>
</c:choose>



</td>
</c:when>
<c:otherwise>
<td><img src="images/folder.ico"/><a href="MyFileManager?key=${f.id}">${f.name}</a></td> 
<td><fmt:formatDate pattern="MM/dd/yyyy hh:mma" value="${f.date}" /></td> 
<td>
</td>
</c:otherwise>
</c:choose>
<td><a href="EditFolder?key=${f.id}">Rename</a> | <a href="DeleteFolder?key=${f.id}">Delete</a></td>


</c:if>
</tr>
</c:forEach>
</c:when>

 

<c:otherwise>
<c:if test="${not empty grand}">
<tr><td><img src="images/folder.ico"/><a href="MyFileManager?key=${grand.id}">\...</a></td>
<td></td>
<td></td>
<td></td>
</tr>
</c:if>
<c:if test="${empty grand}">
<tr><td><img src="images/folder.ico"/><a href="MyFileManager">\...</a></td>
<td></td>
<td></td>
<td></td>
</tr>
</c:if>

<c:forEach items="${Files}" var="f">
<tr>
<c:if test="${f.parent == parentFile}">
<c:choose>
<c:when test="${not f.folder}">
<c:set var="icon" value="blank"/>
<c:set var="filename" value="${f.name}"/>
<c:set  var="ext" value='${fn:split(fn:replace(filename, ".", "|"), "|")}'/>

<c:forEach items="${imgLib}" var="l">
<c:if test="${ext[1] eq l }">
<c:set var="icon" value="${ext[1] }"/>
</c:if>

</c:forEach>


<td><img src="images/${icon}.ico"/><a href="Download?key=${f.id}&name=${f.name}&type=${f.type}">${f.name}</a></td> 
<td><fmt:formatDate pattern="MM/dd/yyyy hh:mma" value="${f.date}" /></td> 


<td>
<c:choose>
<c:when test="${f.size lt 1024 }">
${f.size}B
</c:when>
<c:otherwise>
${f.size/1024}KB
</c:otherwise>
</c:choose>
</td>


</c:when>


<c:otherwise>
<td><img src="images/folder.ico"/><a href="MyFileManager?key=${f.id}">${f.name}</a></td> 
<td><fmt:formatDate pattern="MM/dd/yyyy hh:mma" value="${f.date}" /></td> 
<td>
</td>
</c:otherwise>

</c:choose>
<td><a href="EditFolder?key=${f.id}">Rename</a> | <a href="DeleteFolder?key=${f.id}">Delete</a></td>

</c:if>
</tr>
</c:forEach>
</c:otherwise>
 
</c:choose>



</table>


</div>
<footer><div> Copyright &copy; 2017 Adekola Togunloju All Rights Reserved</div></footer>
</div>






</body>
</html>
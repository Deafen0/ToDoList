<%@ page import="com.example.dulevich.todolist.entity.Task" %>
<%@ page import="com.example.dulevich.todolist.dao.TaskDao" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Save task</title>
</head>
<body>
<h1>Save task</h1>
<form action="${pageContext.request.contextPath}/createTask" method="post">
    <label for="taskName">Task name:</label>
    <input type="text" id="taskName" name="taskName" required><br><br>

    <label for="deadline">Deadline:</label>
    <input type="datetime-local" id="deadline" name="deadline" required><br><br>

    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="TODO">TODO</option>
        <option value="IN_PROGRESS">IN_PROGRESS</option>
        <option value="COMPLETED">COMPLETED</option>
    </select><br><br>

    <input type="submit" value="Save">
</form>


<h2>Созданные задачи:</h2>

<c:forEach var="task" items="${tasks}">
    <p>Task name: ${task.taskName}</p>
    <p>Deadline: ${task.deadline}</p>
    <p>Status: ${task.status}</p>

    <form method="post" action="${pageContext.request.contextPath}/createTask">
        <input type="hidden" name="taskId" value="${task.id}">
        <button type="submit">Delete</button>
    </form>
    <hr>
</c:forEach>

</body>
</html>

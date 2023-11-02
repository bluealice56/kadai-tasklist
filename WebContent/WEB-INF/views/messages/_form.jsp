<%-- フォームのデザイン --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<label for="content_msg">タスク内容</label><br />

<%-- value="${〇●.title}"で、DTOのインスタンスを使って、初期値が入るようにする --%>
<input type="text" name="content" id="content_msg" value="${message.content}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>
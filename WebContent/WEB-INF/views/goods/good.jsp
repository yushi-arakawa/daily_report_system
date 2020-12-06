<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>いいねをした人 一覧ページ</h2>
        <table id="good_list">
            <tbody>
                <tr>
                    <th class="good_name">いいねをした社員の名前</th>
                    <th class="good_date">いいねをした日時</th>
                </tr>
                <c:forEach var="good" items="${goods}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="good_name"><c:out
                                value="${good.employee.name}" /></td>
                        <td class="good_date"><fmt:formatDate
                                value="${good.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${goods_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((goods_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/goods/good?page=${i}&id=${report.id}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/reports/index' />">日報 一覧に戻る</a></p>

    </c:param>
</c:import>
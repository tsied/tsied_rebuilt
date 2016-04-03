

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

			<c:forEach items="${responsMenuList}"  var="menu" varStatus="status">
				<li>
					<a href="${menu.menuLink}" class="dropdown-toggle">
					<i class="menu-icon fa fa-gamepad"></i>
					<span class="menu-text">${menu.menuName}</span>
					</a>
					<b class="arrow"></b>
					<c:if test="${!empty menu.childMenuList}">
						<c:forEach items="${menu.childMenuList}"  var="childMenu">
							<ul class="submenu">
								<li class="">
								<a href="${childMenu.menuLink}" target="_self"><i class="menu-icon fa fa-caret-right"></i>${childMenu.menuName}</a>
								<b class="arrow"></b>
								</li>
							</ul>
						</c:forEach>
					</c:if>
					
					
				</li>
			</c:forEach>

			
	
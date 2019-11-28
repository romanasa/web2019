<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as user>
                <tr>
                    <td class="id">${user.id}</td>
                    <td class="login"><a href="/user/${user.id}">${user.login}</a></td>
                    <td class="creationTime">${user.creationTime}</td>
                    <td class="disabled">
                        <form method="post" action="">
                            <input type="hidden" name="id" value="${user.id}"/>
                            <input type="hidden" name="status" value="<#if user.disabled>enable<#else>disable</#if>"/>
                            <input type="submit" value="<#if user.disabled>Enable<#else>Disable</#if>"/>
                        </form>
                    </td>
                </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>

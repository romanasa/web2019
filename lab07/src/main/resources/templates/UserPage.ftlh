<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>

    <#if user??>
        <div class="user">
            <ul>
                <li>id: ${user.id}</li>
                <li>handle: ${user.login} </li>
                <li>name: ${user.creationTime}</li>
            </ul>
        </div>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>

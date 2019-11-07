<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->

<#import "common.ftl" as c/>

<@c.page>
    <#if handle??>
        <#assign viewedUser=c.findBy(users, "handle", handle)!/>
    <#elseif user_id??>
        <#assign viewedUser=c.findBy(users, "id", user_id)!/>
    </#if>


    <#if viewedUser?? && viewedUser.id??>
        <div class="user">
        <@c.userlink user=viewedUser nameOnly="false"/>
        <ul>
            <li>id: ${viewedUser.id}</li>
            <li>handle: ${viewedUser.handle} </li>
            <li>name: ${viewedUser.name}</li>
            <li><a href="/posts?user_id=${viewedUser.id}">
                    ${c.countPosts(users, viewedUser.id)} post(s)</a></li>


        </ul>
        <img src="/img/user.jpg" alt="user_photo">
        </div>

        <#assign prevUser=c.findPrev(users, "id", viewedUser.id)!/>
        <#assign nextUser=c.findNext(users, "id", viewedUser.id)!/>

        <#if prevUser.id??>
            <a href="/user?handle=${prevUser.handle}">←</a>
        <#else>
            <div class="no_user">←</div>
        </#if>

        <#if nextUser.id??>
            <a href="/user?handle=${nextUser.handle}">→</a>
        <#else>
            <div class="no_user">→</div>
        </#if>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>

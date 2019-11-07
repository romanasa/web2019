<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "common.ftl" as c/>

<@c.page>
    <#assign was = false>
    <#list posts as post>
        <#if post.user_id==user_id>
            <#assign was = true>
            <article>
                <@c.postInformation post/>
                <@c.postText post.text "reduced"/>
                <@c.postRating/>
            </article>
        </#if>
    </#list>
    <#if was== false>
        <h1>No such posts</h1>
    </#if>
</@c.page>
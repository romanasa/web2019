<#-- @ftlvariable name="post_id" type="java.lang.Long" -->

<#import "common.ftl" as c/>

<@c.page>
    <#assign viewedPost=c.findBy(posts, "id", post_id)!/>
    <#if viewedPost.id??>
        <article>
            <@c.postInformation post=viewedPost/>
            <@c.postText text=viewedPost.text flag="full"/>
            <@c.postRating/>
        </article>
    <#else>
        <h1>No such post</h1>
    </#if>
</@c.page>

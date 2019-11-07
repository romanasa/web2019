<#import "common.ftl" as c/>

<@c.page>
    <#list posts as post>
        <article>
            <@c.postInformation post/>
            <@c.postText post.text "reduced"/>
            <@c.postRating/>
        </article>
    </#list>
</@c.page>

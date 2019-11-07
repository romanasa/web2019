<#-- @ftlvariable name="current_uri" type="java.lang.String" -->

<#macro header>
    <header>
        <a href="/"><img src="/img/logo.png" alt="Codeforces" title="Codeforces"/></a>
        <div class="languages">
            <a href="#"><img src="/img/gb.png" alt="In English" title="In English"/></a>
            <a href="#"><img src="/img/ru.png" alt="In Russian" title="In Russian"/></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user=user nameOnly="true"/>
                |
                <a href="#">Logout</a>
            <#else>
                <a href="#">Enter</a>
                |
                <a href="#">Register</a>
            </#if>
        </div>
        <nav>
            <ul>
                <@menu_items "/index" "Index"/>
                <@menu_items "/misc/help" "Help"/>
                <@menu_items "/users" "Users"/>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts as post>
            <section>
                <div class="header">
                    Post #${post.id}
                </div>
                <article>
                    <@postText post.text "reduced"/>
                </article>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        <a href="#">Codeforces</a> &copy; 2010-2019 by Mike Mirzayanov
    </footer>
</#macro>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" type="text/css" href="/css/normalize.css">
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <link rel="icon" href="/favicon.ico" type="image/x-icon"/>
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro noId>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" type="text/css" href="/css/normalize.css">
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <link rel="icon" href="/favicon.ico" type="image/x-icon"/>
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
    <@header/>
    <div class="middle">
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro userlink user nameOnly>
    <a <#if nameOnly=="false">class="${user.color}"</#if>
       href="/user?handle=${user.handle}">${user.handle}</a>
</#macro>

<#macro postInformation post>
    <div class="title"><a href="/post?post_id=${post.id}">${post.title}</a></div>
    <#assign viewedUser=findBy(users, "id", post.user_id)!/>
    <div class="information">By ${viewedUser.name}</div>
</#macro>

<#macro postText text flag>
    <div class="body">
        <#if flag=="full" || text?length<250>
            ${text}
        <#else>
            ${text?substring(0, 247)}...
        </#if>
    </div>
</#macro>

<#macro postRating>
    <div class="postFooter">
        <div class="left">
            <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
            <span class="positive-score">+173</span>
            <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
        </div>
        <div class="right">
            <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
            2 days ago
            <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
            <a href="#">68</a>
        </div>
    </div>
</#macro>

<#macro menu_items url link>
    <li
            <#if current_uri==url>class="current_menu"</#if>>
        <a href=${url}>${link}</a>
    </li>
</#macro>

<#function findNext items key id>
    <#list items as item>
        <#if item[key] gt id>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function findPrev items key id>
    <#list items?reverse as item>
        <#if item[key] lt id>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function findBy items key id>
    <#list items as item>
        <#if item[key]==id>
            <#return item/>
        </#if>
    </#list>
</#function>


<#function countPosts users id>
    <#assign cnt=0/>
    <#list posts as post>
        <#if post.user_id==id>
            <#assign cnt = cnt + 1/>
        </#if>
    </#list>
    <#return cnt>
</#function>
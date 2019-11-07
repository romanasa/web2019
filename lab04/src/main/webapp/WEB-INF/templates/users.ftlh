<#import "common.ftl" as c/>

<@c.page>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>User</th>
                <th>Handle</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td><a href="/user?handle=${user.handle}">${user.name}</a></td>
                    <td><@c.userlink user "false"/></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>

<#-- @ftlvariable name="targetUserLogin" type="java.lang.String" -->
<#-- @ftlvariable name="sourceUserLogin" type="java.lang.String" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="sendMessage">
        <form method="post" action="">
            <input type="hidden" name="action" value="sendMessage">
            <div class="field">
                <div class="select">
                    Select user:
                </div>
                <div class="selectValue">
                    <label>
                        <select name="targetUserLogin">
                            <#list users as user>
                                <option value="${user.login}">${user.login}</option>
                            </#list>
                        </select>
                    </label>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="text">Text:</label>
                </div>
                <div class="value">
                    <textarea id="text" name="text" value="${text!}"></textarea>
                </div>
            </div>
            <div class="button-field">
                <input type="submit" value="Send">
            </div>
        </form>
    </div>
    <div class="datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Text</th>
                <th>When</th>
            </tr>
            </thead>
            <tbody>
            <#list talks as talk>
                <tr>
                    <td>${talk.id}</td>
                    <td>${talk.sourceUserLogin}</td>
                    <td>${talk.targetUserLogin}</td>
                    <td>${talk.text}</td>
                    <td>${talk.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
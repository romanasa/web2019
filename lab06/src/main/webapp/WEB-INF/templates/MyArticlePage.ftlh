<#-- @ftlvariable name="myArticles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles datatable">
        <div class="caption">Articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide/Show</th>
            </tr>
            </thead>
            <tbody>
            <#list myArticles as article>
                <tr>
                    <td class="article_id">${article.id}</td>
                    <td class="title">${article.title}</td>
                    <td class="view">
                        <input type="button" value="<#if article.hidden>Show<#else>Hide</#if>" name="${article.id}">
                    </td>
                </tr>
            </#list>
            <#if myArticles?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No articles.
                    </td>
                </tr>
            </#if>
            </tbody>
        </table>
    </div>
    <script>
        $(function() {

            $(" input").on("click", function(response) {
                var $this = this;
                var id = $(this).attr("name");
                var val = $(this).val();

                if (val === "Hide") {
                    ajax({ action: "setHidden",
                            articleId: id},
                        function(response) {
                            $($this).prop("value", "Show");
                        });
                } else {
                    ajax({ action: "setShown",
                            articleId: id},
                        function(response) {
                            $($this).prop("value", "Hide");
                        });
                }
            });
        })
    </script>
</@c.page>
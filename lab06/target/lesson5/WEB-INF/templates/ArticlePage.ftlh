<#-- @ftlvariable name="title" type="java.lang.String" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable name="hidden" type="java.lang.Boolean" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="createArticle">
        <form method="post" action="">
            <input type="hidden" name="action" value="createArticle"/>
            <div class="createTitle">
                <div class="name">
                    <label for="title">Title:</label>
                </div>
                <div class="value">
                    <input type="text" id="title" name="title" value="${title!}"/>
                </div>
            </div>
            <div class="hidden">
                <div class="name">
                    <label for="hidden">Hidden:</label>
                </div>
                <div class="value">
                    <input type="checkbox" id="hidden" name="hidden"/>
                </div>
            </div>
            <div class="createText">
                <div class="name">
                    <label for="text">Text:</label>
                </div>
                <div class="value">
                    <textarea id="text" name="text" value="${text!}"></textarea>
                </div>
            </div>
            <div class="error">
            </div>
            <div class="button-field">
                <input type="submit" value="Create">
            </div>
        </form>
    </div>

    <script type="text/javascript">
        $(function() {
            $(" form").submit(function () {
                var title = $(this).find("input[name='title']").val();
                var hidden = $(this).find("input[name='hidden']").is(':checked');
                var text = $(this).find("textarea[name='text']").val();
                var $error = $(this).find(".error");

                ajax({ action: "createArticle",
                        title: title,
                        text: text,
                        hidden: hidden },
                    function(response) {
                        if (response["error"]) {
                            $error.text(response["error"]);
                        }
                    });

                return false;
            });
        })
    </script>
</@c.page>
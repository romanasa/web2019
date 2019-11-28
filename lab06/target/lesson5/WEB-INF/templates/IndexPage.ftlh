<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>

    <div class="noData">
        No data
    </div>
    <article>
        <template>
            <div class="title"></div>
            <div class="information"></div>
            <div class="body"></div>
            <div class="footer">
                <div class="left">
                    <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#">68</a>
                </div>
            </div>
        </template>
    </article>

    <script>
        $(function () {
            var $noData = $(".noData");
            $noData.text("Loading...");

            var users = {};

            $("aside section").click(function () {
                $(this).css("background-color", "red");
            });

            ajax({ action: "findAll" },
                
                function(response) {
                    ajax({ action: "findAllUsers" },
                        function (response) {
                            response["users"].forEach(function (user) {
                                users[user["id"]] = user["login"];
                            })
                        }
                    );

                    response["shownArticles"].forEach(function(article) {
                        $noData.hide();
                        var $article = $($(" article template").prop("content")).clone();
                        $article.find(".title").text(article["title"]);
                        $article.find(".information").text(article["userLogin"] + ", " + article["creationTime"]);
                        $article.find(".body").text(article["text"]);
                        $(" article").append($article);
                    });
                });

        })
    </script>
</@c.page>

package ru.itmo.tpl.util;

import ru.itmo.tpl.model.COLOR;
import ru.itmo.tpl.model.User;
import ru.itmo.tpl.model.Post;

import java.util.*;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirayanov", "Mikhail Mirzayanov", COLOR.RED),
            new User(2, "tourist", "Genady Korotkevich", COLOR.BLUE),
            new User(3, "emusk", "Elon Musk", COLOR.GREEN),
            new User(5, "pashka", "Pavel Mavrin", COLOR.RED),
            new User(7, "geranazavr555", "Georgiy Nazarov", COLOR.GREEN),
            new User(1100, "cannon147", "Erofey Bashunov", COLOR.BLUE)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Test", "Hello, world!", 1),
            new Post(2, "Короткий текст", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor ", 3),
            new Post(7, "Описание трейлера", "Главный герой — 27-летний Илья Горюнов, семь лет отсидевший в тюрьме по ложному обвинению в распространении наркотиков. Когда Илья выходит на свободу, он понимает, что прежняя жизнь, по которой он тосковал, разрушена, и вернуться к ней он больше не сможет. Хотя он не собирался мстить человеку, который отправил его в тюрьму, другого выхода теперь нет. Встретившись лицом к лицу со своим обидчиком, Илья совершает необдуманный поступок, после которого главный герой получает доступ к смартфону Петра, а с ним и к жизни молодого человека — его фотографиям и видео, перепискам с родителями и девушкой Ниной, к странным, полным недомолвок и угроз переговорам с коллегами. На время Илья становится для всех Петром — через текст на экране телефона.", 7),
            new Post(3, "Второй текст", "Классический текст Lorem Ipsum, используемый с XVI века\n" +
                    "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" +
                    "\n" +
                    "Абзац 1.10.32 \"de Finibus Bonorum et Malorum\", написанный Цицероном в 45 году н.э.\n" +
                    "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"", 3),
            new Post(5, "Ололо", "Я тролль", 1100),
            new Post(10, "Технокубок", "Mail.Ru Group совместно с МФТИ, МГТУ им. Н. Э. Баумана и Codeforces в пятый раз запускает олимпиаду по программированию для школьников — «Технокубок». В этом учебном году олимпиада вошла в перечень олимпиад школьников, повысив свой уровень до самого высокого — первого: теперь победители и призеры могут быть зачислены на профильные факультеты российских вузов без вступительных экзаменов. Кроме того лучшие участники получат ценные призы от компании Apple.\n" +
                    "\n" +
                    "Сразиться за звание самого талантливого молодого программиста приглашаются учащиеся средних образовательных учреждений 8-11 классов.\n" +
                    "\n" +
                    "Победители и призеры олимпиады будут определены по результатам очного этапа, который пройдет 1 марта 2020 года на базе МФТИ, МГТУ им. Н.Э.Баумана, а также на других региональных площадках по всей России, о которых будет сообщено позднее.", 1)
    );

    private static List<User> getUsers() {
        return USERS;
    }

    private static List<Post> getPosts() {
        return POSTS;
    }

    public static void putData(Map<String, Object> data) {
        data.put("users", getUsers());
        for (User user : getUsers()) {
            if (data.get("logged_user_id") != null &&
                    Long.toString(user.getId()).equals(data.get("logged_user_id").toString())) {
                data.put("user", user);
            }
        }
        List<Post> posts = new ArrayList<>(getPosts());
        Collections.reverse(posts);
        data.put("posts", posts);
    }
}

for ((i=1; i < 101; i++))
do
    x=$(($i * $i)) 
    eval "curl 'http://1d3p.wp.codeforces.com/new' -H 'Connection: keep-alive' -H 'Cache-Control: max-age=0' -H 'Origin: http://1d3p.wp.codeforces.com' -H 'Upgrade-Insecure-Requests: 1' -H 'Content-Type: application/x-www-form-urlencoded' -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3' -H 'Referer: http://1d3p.wp.codeforces.com/' -H 'Accept-Encoding: gzip, deflate' -H 'Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7' -H 'Cookie: JSESSIONID=96F2AFC0B72C8B98E4ACBA09769D6903' --data '_af=34be50b38beccce4&proof="$x"&amount="$i"&submit=Submit' --compressed --insecure"

done

let baseUrl = 'http://localhost:8082/lxl'
function login() {
    let username = $('input[name=username]').val()
    let password = $('input[name=password]').val()
    $.ajax({
        type: 'post',
        url: baseUrl + '/login',
        dataType: 'json',
        data: {'username': username, 'password': password},
        success: function (res) {
            console.log(res)
            if (res && res.code === 0) {
                location.href = baseUrl + '/index'
            } else {
                alert(res.msg)
            }
        }
    })
}
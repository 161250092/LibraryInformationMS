var vm = new Vue({
    el: "#notification",

    data: {
        updates: [{
            updateId: 0,
            user: {
                userId: 0,
                userName: "",
                balance: 0.0
            },
            content: "",
            updateTime: new Date()
        }],

        loading: {},
    },

    methods: {
        confirmUpdate(updateId) {
            this.loadings()
        },

        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);
        },

        search() {
            this.$http.get("/notification/receive").then(result => {
                console.log(result);
                this.update = result.body;
                this.loading.close()
            })
        }
    }
});


// polling
$(document).ready(function(){
    var timer = setInterval(function(){ajax_receive(timer)}, 3000);
});

function ajax_receive(timer) {
    var toUrl = "{:U('Order/ajax_get_pay_status')}";
    if ($("#out_trade_no").val() != 0) {
        $.post(
            toUrl,
            {out_trade_no:$("#out_trade_no").val()},
            function (res) {
                if (res.status == 1) { //订单状态为1表示支付成功
                    //此处可以进行相应业务代码的编写，例如支付成功提示，或者直接进行页面跳转.
                    clearInterval(timer);
                }
            },"JSON");
    }
}
var vm = new Vue({
    el: "#app",

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
        },

        created() {
            console.log("Created!")
        }
    }
});


// polling
$(document).ready(function(){
    var timer = setInterval(function(){ajax_receive(timer)}, 3000);
});

function ajax_receive(timer) {
    $.search();
}
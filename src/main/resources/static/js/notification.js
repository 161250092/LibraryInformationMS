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
    },

    methods: {
        confirmUpdate(updateId) {

        },

        search() {
            this.$http.get("/notification/receive").then(result => {
                console.log(result);
                this.updates = result.body;
            })
        },
    },

    created() {
        this.search()
    },
});


// polling
// $(document).ready(function(){
//     var timer = setInterval(function(){ajax_receive(timer)}, 3000);
// });
//
// function ajax_receive(timer) {
//     $.search();
// }
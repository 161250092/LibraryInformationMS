Vue.http.options.emulateJSON = true;

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
            this.$http.post("/notification/confirm", JSON.stringify(updateId)).then(
                this.search()
            )
        },

        search() {
            this.$http.get("/notification/receive").then(result => {
                console.log(result);
                this.updates = result.body;
            })
        },
    },

    created() {
        this.search();

        //polling
        var timer = setInterval(function(){
            vm.search();
        }, 3000);
    },
});



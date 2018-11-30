<template>
    <div id="app">
        <TodoHeader></TodoHeader>
        <TodoInput v-on:addTodo="addTodo"></TodoInput>
        <TodoList @removeTodo="removeTodo"
                  v-bind:propsdata="todoItems"></TodoList>
        <TodoFooter v-on:removeAll="clearAll"></TodoFooter>
    </div>
</template>

<script>
    import axios from './libs/axios.custom.js';

    import TodoHeader from './components/TodoHeader.vue';
    import TodoFooter from './components/TodoFooter.vue';
    import TodoList from './components/TodoList.vue';
    import TodoInput from './components/TodoInput.vue';

    export default {
        data() {
            return {
                todoItems: [],
            };
        },
        methods: {
            addTodo(todoItem) {
                if (!todoItem) {
                    return;
                }

                localStorage.setItem(todoItem, todoItem);
                this.todoItems.push(todoItem);
            },
            removeTodo(todoItem, index) {
                localStorage.removeItem(todoItem);
                this.todoItems.splice(index, 1);
            },
            clearAll() {
                localStorage.clear();
                this.todoItems = [];
            },
        },
        components: {
            TodoHeader,
            TodoFooter,
            TodoList,
            TodoInput,
        },
        created() {
            axios.get('/api/todos').then((res) => {
                this.todoItems = res.data || [];
            }).catch((err) => {
                console.error(err.message);
            });
        },
    };
</script>

<style>
    body {
        text-align: center;
        background-color: #F6F6F8;
    }

    input {
        border-style: groove;
        with: 200px;
    }

    button {
        border-style: groove;
    }

    /*noinspection CssUnusedSymbol*/
    .shadow {
        box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03)
    }
</style>

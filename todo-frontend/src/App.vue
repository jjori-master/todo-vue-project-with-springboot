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
    const length = localStorage.length - 1;

    if (length < 1) {
      return;
    }
    new Array(length).fill(null).forEach((p, idx) => {
      this.todoItems.push(localStorage.key(idx));
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

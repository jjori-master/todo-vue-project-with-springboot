<template>
  <section>
    <!--suppress HtmlUnknownTag -->
    <transition-group name="list" tag="ul">
      <li class="shadow"
          v-for="(todoItem, index ) in propsdata"
          v-bind:key="todoItem">
        <i class="checkBtn fa fa-check" aria-hidden="true"></i>
        {{todoItem}}
        <span class="removeBtn"
              @click="removeTodo(todoItem, index)">
          <i class="fa fa-trash-o" aria-hidden="true"></i>
        </span>
      </li>
    </transition-group>
  </section>
</template>

<script>
export default {
  props: ['propsdata'],
  name: 'TodoList',
  methods: {
    removeTodo(todoItem, index) {
      this.$emit('removeTodo', todoItem, index);
    },
  },
};
</script>

<style scoped>
  ul {
    list-style-type: none;
    padding-left: 0;
    margin-top: 0;
    text-align: left;
  }

  li {
    display: flex;
    min-height: 50px;
    height: 50px;
    line-height: 50px;
    margin: 0.5rem 0;
    padding: 0 0.9rem;
    background: white;
    border-radius: 5px;
  }

  .checkBtn {
    line-height: 45px;
    color: #62acde;
    margin-right: 5px;
  }

  .removeBtn {
    margin-left: auto;
    color: #de4343;
  }

  /*noinspection CssUnusedSymbol*/
  .list-item {
    display: inline-block;
    margin-right: 10px;
  }
  /*noinspection CssUnusedSymbol*/
  .list-move {
    transition: transform 1s;
  }

  /*noinspection CssUnusedSymbol*/
  .list-enter-active, .list-leave-active {
    transition: all 1s;
  }

  /*noinspection CssUnusedSymbol*/
  .list-enter, .list-leve-to {
    opacity: 0;
    transform: translateY(30px);
  }
</style>

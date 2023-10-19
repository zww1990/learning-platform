import { IconShare, IconDel, IconWriting, IconAscending, IconClockWork } from '@opentiny/vue-icon'
import { ref } from 'vue'

export default {
  setup() {
    const value = ref('')
    return { value }
  },
  components: {
      IconShare: IconShare(),
      IconDel: IconDel(),
      IconWriting: IconWriting(),
      IconAscending: IconAscending(),
      IconClockWork: IconClockWork()
  },
  template: `
    <tiny-button>TinyVue</tiny-button>
    <tiny-alert description="TinyVue"></tiny-alert>
    <icon-share class="tiny-svg-size icon-share"></icon-share>
    <icon-del class="tiny-svg-size icon-del"></icon-del>
    <icon-writing class="tiny-svg-size icon-writing"></icon-writing>
    <icon-ascending class="tiny-svg-size icon-ascending"></icon-ascending>
    <icon-clock-work class="tiny-svg-size icon-clockWork"></icon-clock-work>
    <tiny-date-picker type="datetime" v-model="value"></tiny-date-picker>
  `
}

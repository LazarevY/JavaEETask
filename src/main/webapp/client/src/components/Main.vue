<template>
  <div>
    <div class="container-fluid">
      <div class="row">
        <div class="col-12 col-md-8">
          <br>
          <table class="table table-bordered">
            <tr v-for="event in events">
              <td v-if="event.gift != null">Birthday</td>
              <td v-if="event.time != null">Appointment</td>
              <td>{{ event.targetPerson }}</td>
              <td>{{ event.description }}</td>
              <td>{{ event.eventDate.year }}-{{ event.eventDate.month }}-{{ event.eventDate.day }}</td>
              <td v-if="event.gift != null">{{ event.gift }}</td>
              <td v-if="event.time != null">{{ event.time.hour }}:{{ event.time.minute }}</td>
              <td>
                <button v-b-modal.edit-modal @click="openEdit(event)" class="btn btn-warning">Edit</button>
                <button @click="deleteEvent(event)" class="btn btn-danger">Delete</button>
              </td>
            </tr>
          </table>
        </div>
        <div class="col-6 col-md-4">
          <b-form>
            <b-form-group label="Types">
              <b-form-checkbox-group v-model="filterForm.filter_type_selected"
                                     :options="filter_type_options"
                                     value-field="item"
                                     text-field="name">
              </b-form-checkbox-group>
            </b-form-group>


            <b-form-group label="Sort parameter"
            >
              <b-form-radio-group
                v-model="filterForm.sort"
                :options="sortValues">

              </b-form-radio-group>
            </b-form-group>

            <b-form-group>
              <b-checkbox v-model="filterForm.sortDesc">
                Descending
              </b-checkbox>
            </b-form-group>

            <b-form-group label="Filter start date">
              <table>
                <tr>
                  <td>
                    <datepicker v-model="filterForm.startDate"
                                :disabledDates="{from: filterForm.endDate}"></datepicker>
                  </td>
                  <td>
                    <b-button @click="filterForm.startDate = null">Reset</b-button>
                  </td>
                </tr>
              </table>
            </b-form-group>

            <b-form-group label="Filter end date">
              <table>
                <tr>
                  <td>
                    <datepicker v-model="filterForm.endDate"
                                :disabledDates="{to: filterForm.startDate}"></datepicker>
                  </td>
                  <td>
                    <b-button @click="filterForm.endDate = null">Reset</b-button>
                  </td>
                </tr>
              </table>
            </b-form-group>

            <b-button @click="onFilterSubmit" variant="primary">Filter</b-button>
          </b-form>

          <br>

          <b-form-group>
            <b-button v-b-modal.add-modal variant="primary">Add new event</b-button>
          </b-form-group>

        </div>
      </div>

    </div>

    <b-modal hide-footer ref="addModal" id="add-modal" title="Add new event">
      <b-form>
        <b-radio-group :options="addForm.types"
                       v-model="addForm.type">
        </b-radio-group>

        <b-form-group label="Person">
          <b-input v-model="addForm.person" type="text" required></b-input>
        </b-form-group>

        <b-form-group label="Description(optional)">
          <b-input v-model="addForm.desc" type="text" required></b-input>
        </b-form-group>

        <b-form-group>
          <datepicker v-model="addForm.date"></datepicker>
        </b-form-group>

        <b-form-group label="Gift" v-if="addForm.type === 'birthday'">
          <b-input v-model="addForm.gift" type="text" required></b-input>
        </b-form-group>

        <b-form-group label="Time" v-if="addForm.type === 'appointment'">
          <vue-timepicker v-model="addForm.time" format="HH:mm"></vue-timepicker>
        </b-form-group>

        <b-button @click="onAddSubmit" variant="primary">Add</b-button>
      </b-form>
    </b-modal>

    <b-modal hide-footer ref="editModal" id="edit-modal" title="Edit event">
      <b-form>
        <b-form-group label="Person">
          <b-input v-model="editForm.person" type="text" required></b-input>
        </b-form-group>

        <b-form-group label="Description(optional)">
          <b-input v-model="editForm.desc" type="text" required></b-input>
        </b-form-group>

        <b-form-group>
          <datepicker name="editDatePick" v-model="editForm.date"></datepicker>
        </b-form-group>

        <b-form-group label="Gift" v-if="editForm.type === 'birthday'">
          <b-input v-model="editForm.gift" type="text" required></b-input>
        </b-form-group>

        <b-form-group label="Time" v-if="editForm.type === 'appointment'">
          <vue-timepicker v-model="editForm.time" format="HH:mm"></vue-timepicker>
        </b-form-group>

        <b-button @click="onEditSubmit" variant="primary">Edit</b-button>
      </b-form>
    </b-modal>


  </div>
</template>

<script>
import axios from "axios";
import Datepicker from "vuejs-datepicker";
import VueTimepicker from "vue2-timepicker";

export default {
  name: 'Main',
  components: {
    Datepicker,
    VueTimepicker,
  },
  data() {
    return {
      name: '',
      events: [],
      filter_type_options: [
        {item: 'birthday', name: 'Birthdays'},
        {item: 'appointment', name: 'Appointments'}
      ],
      sortValues: [
        {value: 'month', text: 'Month'},
        {value: 'day', text: 'Day'},
        {value: 'year', text: 'Year'},
      ],
      filterForm: {
        filter_type_selected: [],
        startDate: null,
        endDate: null,
        sortDesc: false,
        sort: 'year',
      },
      addForm: {
        types: [
          {value: 'birthday', text: 'Birthday'},
          {value: 'appointment', text: 'Appointment'},
        ],
        type: 'birthday',
        person: '',
        desc: '',
        date: Date.now(),
        gift: '',
        time: {
          HH: 0,
          mm: 0,
        }
      },
      editForm:{
        id: null,
        type: 'birthday',
        person: '',
        desc: '',
        date: null,
        gift: '',
        time: {
          HH: 0,
          mm: 0,
        }
      }
    }
  },
  methods: {
    openEdit(event){
      this.editForm.id = event.id;
      this.editForm.date = new Date(event.eventDate.year, event.eventDate.month - 1,event.eventDate.day);
      this.editForm.person = event.targetPerson;
      this.editForm.desc = event.description;
      if (event.time != null) {
        this.editForm.type = 'appointment';
        this.editForm.time = {HH: event.time.hour, mm: event.time.minute};
      }
      else if (event.gift != null) {
        this.editForm.gift = event.gift;
        this.editForm.type = 'birthday'
      }
    },
    onAddSubmit(){
      let payload = {
        type: this.addForm.type,
        person: this.addForm.person,
        desc: this.addForm.desc,
        gift: this.addForm.gift,
        date: this.dateToString(this.addForm.date),
        time: this.addForm.time,
      }
      this.$refs.addModal.hide();
      this.addEvent(payload);
    },
    deleteEvent(event){
      const path = 'http://localhost:8090/main/edit';

      let type = event.time == null? 'birthday' : 'appointment';

      axios.put(path, {
        id: event.id,
        type: type,
      }).then(() => {
        this.onFilterSubmit();
      }).catch((error) => {
        console.log(error)
      })
    },
    addEvent(payload){
      const path = 'http://localhost:8090/main/add';

      axios.post(path, payload).then(() => {
        this.onFilterSubmit();
      }).catch((error) => {
        console.log(error)
      })
    },
    dateToString(date){
      if (date == null)
        return null;
      let month = date.getMonth() + 1 + 100;
      let day = date.getDate() + 100;
      return date.getFullYear().toString() + "-" +
        month.toString().substr(1,2) + "-" + day.toString().substr(1,2);
    },
    onEditSubmit(){
      let payload = {
        id: this.editForm.id,
        type: this.editForm.type,
        person: this.editForm.person,
        desc: this.editForm.desc,
        gift: this.editForm.gift,
        date: this.dateToString(this.editForm.date),
        time: this.editForm.time,
      }
      this.$refs.editModal.hide();
      this.editEvent(payload);
    },
    editEvent(payload){
      const path = 'http://localhost:8090/main/edit';

      axios.post(path, payload).then(() => {
        this.onFilterSubmit();
      }).catch((error) => {
        console.log(error)
      })
    },
    onFilterSubmit() {
      let types = this.filterForm.filter_type_selected;
      let payload = {
        types: types,
        sort: this.filterForm.sort,
        sortDesc: this.filterForm.sortDesc,
        beginDate: this.dateToString(this.filterForm.startDate),
        endDate: this.dateToString(this.filterForm.endDate),
      }
      this.loadFiltered(payload)
    },
    loadFiltered(payload) {
      const path = 'http://localhost:8090/main';

      axios.post(path, payload).then((resp) => {
        this.events = resp.data.events;
      }).catch((error) => {
        console.log(error)
      })
    },
  },
  created() {
    const path = 'http://localhost:8090/main';

    axios.get(path).then((resp) => {
      this.events = resp.data.events;
    }).catch((error) => {
      console.log(error)
    })
  }
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

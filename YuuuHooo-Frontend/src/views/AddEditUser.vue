<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, onMounted, computed } from "vue";
import sasNav from "../components/sasNav.vue";
import useVuelidate from "@vuelidate/core";
import { required, email, maxLength } from "@vuelidate/validators";

const API_ROOT = import.meta.env.VITE_ROOT_API;
const router = useRouter();
const { params } = useRoute();

const userInfo = ref({});

const type = ref(params.type);

onMounted(async () => {
  if (params.id !== undefined) {
    type.value = "edit";
    // console.log(type.value)
    try {
      const res = await fetch(`${API_ROOT}/api/users/${params.id}`);
      if (res.status === 200) {
        userInfo.value = await res.json();
        createCompareIfo(userInfo.value);
      } else if (res.status !== 200) {
        alert("The requested page is not available!");
        router.push("/admin/users");
      } else throw new Error("No Announcement");
    } catch (error) {
      console.log(error);
    }
  } else if (params.id === undefined) {
    type.value = "add";
    // console.log(type.value)
    userInfo.value = {
      username: "",
      password: "",
      name: "",
      email: "",
      role: "",
    };

    // console.log(userInfo.value);
  }
});

const compareInfo = {};
const createCompareIfo = (duplicateInfo) => {
  for (const keys in duplicateInfo) {
    compareInfo[keys] = duplicateInfo[keys];
  }

  // console.log(compareInfo)
};

const rules = computed(() => {
  return {
    username: { required, maxLength: maxLength(45) },
    name: { required, maxLength: maxLength(100) },
    email: { required, email, maxLength: maxLength(150) },
    role: { required },
  };
});

const v$ = useVuelidate(rules, userInfo);

function shallowEquality(object1, object2) {
  const keys1 = Object.keys(object1);
  const keys2 = Object.keys(object2);
  if (keys1.length !== keys2.length) {
    return false;
  }
  for (let key of keys1) {
    if (object1[key] !== object2[key]) {
      return false;
    }
  }
  return true;
}

const cancelAdding = (type) => {
  //  confirm("Do you want to cancel adding a user?") === true ? type === 'user' ? router.push('/admin/user')  : alert("No")
  if (confirm("Do you want to cancel adding a user?")) {
    if (type === "user") {
      router.push("/admin/user");
    } else if (type === "announcement") {
      router.push("/admin/announcement");
    }
    else if (type === "match") {
      router.push("/admin/user/match");
    }
  } 
  };

const error = ref([]);
const saveAdding = async (information) => {
  const isValid = await v$.value.$validate();
  if (isValid) {
    if (type.value === "add") {
      if (confirm("Are you sure the information is correct?") === true) {
        try {
          const res = await fetch(`${API_ROOT}/api/users`, {
            method: "POST",
            headers: {
              "content-type": "application/json",
            },
            body: JSON.stringify(information),
          });
          if (res.status === 200) {
            const users = await res.json();
            console.log(users);
            await router.push({ name: "UserList" });
          } else {
            const errorResponse = await res.json();
            error.value = errorResponse.detail;
            // console.log(error.value);
          }
        } catch (error) {
          console.log(error);
        }
      }
    } else if (type.value === "edit") {
      if (shallowEquality(information, compareInfo) !== true) {
        if (confirm("Are you sure the information is correct?") === true) {
          try {
            const res = await fetch(`${API_ROOT}/api/users/${params.id}`, {
              method: "PUT",
              headers: {
                "content-type": "application/json",
              },
              body: JSON.stringify(information),
            });
            if (res.status === 200) {
              const users = await res.json();
              // console.log(users)
              console.log("backend updated");
              await router.push({ name: "UserList" });
            } else {
              const errorResponse = await res.json();
              error.value = errorResponse.detail;
              // console.log(error.value);
            }
          } catch (error) {
            console.log(error);
          }
        }
      } else {
        router.push({ name: "UserList" });
      }
    }
  }

  //router push + fetch
};

// const saveBtn = ref(true)
const saveBtn = computed(() => {
  const saveBtn = ref(true);
  if (Object.values(userInfo.value).includes("")) {
    return (saveBtn.value = true);
  } else {
    return (saveBtn.value = false);
  }
});

const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
// console.log(typeof timezone)

const convertTZ = (date) => {
  if (typeof date === "string") {
    const convertDate = new Date(date);
    const options = {
      day: "numeric",
      month: "short",
      year: "numeric",
      hour: "numeric",
      minute: "numeric",
      timeZone: timezone,
    };
    return convertDate.toLocaleDateString("en-GB", options); //format + convert
  } else {
    return "-";
  }
};

const indicateError = (field) => {
  for (const e of error.value) {
    if (e.field === field) {
      // console.log(e.errorMessage);
      return e.errorMessage;
    }
  }
};

const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#&*_])(?!.*\s).{8,14}$/;

const checkPatternPasswResponse = ref("")
const checkPatternPassw = (password) => {
  console.log(regexPassword.test(password))

  if (!regexPassword.test(password)) {
    checkPatternPasswResponse.value =
      "The password must be between 8 and 14  characters, at least 1 of uppercase, lowercase, number, special characters and no whitespace";
  }else if(regexPassword.test(password) || password.length === 0){
    checkPatternPasswResponse.value = ""
 }
};

//
const confirmPassword = ref("")
const passwordEqualStatus = ref("")
const checkConfirmPassword = () => {
  if(userInfo.value.password !== confirmPassword.value){
    passwordEqualStatus.value = "The password DOES NOT Match!"
  }else{
    passwordEqualStatus.value = ""
  }
}



</script>

<template>
  <div class="flex flex-row">
    <sasNav @cancelAnn="cancelAdding" @cancelUser="cancelAdding" @cancelMatch="cancelAdding"/>
    <div class="m-5 w-full h-[45em]">
      <div
        :class="
          type === 'edit'
            ? 'border-2 border-black p-5 m-5 h-[57em]'
            : 'border-2 border-black p-5 m-5 h-[57em]'
        "
      >
        <div class="text-[2em] font-['Acme']">User Detail:</div>
        <form @submit.prevent="saveAdding(userInfo)">
          <div class="flex flex-col mb-[2em] mt-[2em]">
            <div class="text-[1.5em] font-['Acme']">Username</div>
            <input
              type="text"
              required
              maxlength="45"
              placeholder="username"
              v-model.trim="userInfo.username"
              class="ann-username border-2 border-black rounded-md w-[70em] h-[2em] pl-[6px]"
            />
            <span class="text-red-500 ml-1 ann-error-username">{{indicateError("username")}}</span>
          </div>
          <div class="flex flex-col mb-[2em]" v-if="type !== 'edit'">
            <div class="text-[1.5em] font-['Acme']">Password</div>
            <input
              type="password"
              placeholder="********"
              required
              minlength="8"
              maxlength="14"
              v-model="userInfo.password"
              @input="checkPatternPassw(userInfo.password)"
              class="ann-password border-2 border-black rounded-md w-[70em] h-[2em] pl-[6px]"
            />
            <span class="text-red-500 ml-1 ann-error-password">
              {{ checkPatternPasswResponse }}</span
            >
          </div>
          <div class="flex flex-col mb-[2em]" v-if="type !== 'edit'">
            <div class="text-[1.5em] font-['Acme']">Confirm Password</div>
            <input
              type="password"
              placeholder="********"
              required
              minlength="8"
              maxlength="14"
              v-model="confirmPassword"
              @input="checkConfirmPassword()"
              class="ann-confirm-password border-2 border-black rounded-md w-[70em] h-[2em] pl-[6px]"
            />
            <span class="text-red-500 ml-1 ann-error-confirm-password"> {{ passwordEqualStatus }}</span>
          </div>
          <div class="flex flex-col mb-[2em]">
            <div class="text-[1.5em] font-['Acme']">Name</div>
            <input
              type="text"
              required
              maxlength="100"
              placeholder="name"
              v-model.trim="userInfo.name"
              class="ann-name border-2 border-black rounded-md w-[70em] h-[2em] pl-[6px]"
            />
            <span class="text-red-500 ml-1 ann-error-name">
              {{ indicateError("name") }}</span
            >
          </div>
          <div class="flex flex-col mb-[2em]">
            <div class="text-[1.5em] font-['Acme']">Email</div>
            <input
              type="email"
              required
              maxlength="150"
              placeholder="email"
              v-model.trim="userInfo.email"
              class="ann-email border-2 border-black rounded-md w-[70em] h-[2em] pl-[6px]"
            />
            <span class="text-red-500 ml-1 ann-error-email">
              {{ indicateError("email") }}</span
            >
          </div>
          <div class="flex flex-col mb-[2em]">
            <div class="text-[1.5em] font-['Acme']">Role</div>
            <select
              required
              v-model="userInfo.role"
              class="ann-role border-2 border-black rounded-md w-[10em] p-[5px]"
            >
              <option disabled value="">choose your role</option>
              <option value="admin">admin</option>
              <option value="announcer">announcer</option>
            </select>
          </div>
          <div class="flex flex-row gap-40 mb-[2em]" v-if="type === 'edit'">
            <div class="ann-created-on text-[1em] font-['Acme']">
              Created On: {{ convertTZ(userInfo.createdOn) }}
            </div>
            <div class="ann-updated-on text-[1em] font-['Acme']">
              Updated On: {{ convertTZ(userInfo.updatedOn) }}
            </div>
          </div>
          <div class="flex flex-row gap-2">
            <button
              type="submit"
              class="ann-button border-2 btn btn-active text-[1em] font-['Acme'] bg-slate-200 text-black hover:bg-green-300"
              :disabled="false"
            >
              Save
            </button>
            <button
              type="button"
              class="ann-button border-2 btn btn-active text-[1em] font-['Acme'] bg-slate-200 text-black hover:bg-red-400"
              @click="cancelAdding('user')"
            >
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

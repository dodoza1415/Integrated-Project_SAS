const API_ROOT = import.meta.env.VITE_ROOT_API;

export const getUsers = async () => {
  try {
    const res = await fetch(
      `${API_ROOT}/api/users`
    );
    if (res.status === 200) {
      const users = await res.json();
      return users;
    } else throw new Error("No Users");
  } catch (error) {
    console.log(error);
  }
};

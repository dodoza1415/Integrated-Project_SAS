const API_ROOT=import.meta.env.VITE_ROOT_API;

export const getCategories = async () => {
  try {
    const res = await fetch(`${API_ROOT}/api/categories`);
    if (res.status === 200) {
      const categories = await res.json();
      return categories;
    } else throw new Error("No Category");
  } catch (error) {
    console.log(error);
  }
};
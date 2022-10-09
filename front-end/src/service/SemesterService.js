import api from "./index";

export const getAllSemesters = () => {
    return api.get("/semester/getall")
}

export const getLastUpdate = (semester) => {
    return api.get("/semester/lastupdate/" + semester.year + "-" + semester.code)
}
import api from "./index";

export const fetchDataFromCsv = (key, file, semesterName, semesterYear, semesterCode) => {
    const semester = {
        "semesterName": semesterName,
        "year": semesterYear,
        "code": semesterCode
    }

    const form = new FormData()
    form.append("key", key);
    form.append("semester", JSON.stringify(semester));
    form.append("file", file);
    return api.post("/fetch/data", form);
}
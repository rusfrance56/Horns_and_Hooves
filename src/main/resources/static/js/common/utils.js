function clearEmptyValues(filter) {
    var clearedFilter = {};

    if (filter !== undefined) {
        for (var i in filter) {
            if (filter[i] !== "") {
                clearedFilter[i] = filter[i];
            }
        }
    }
    return clearedFilter

}

function isUndefinedOrNull(val) {
    return val === undefined || val === null
}
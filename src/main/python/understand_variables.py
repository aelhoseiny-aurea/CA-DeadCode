import sys
import understand

db = sys.argv[1]

# Open Database
db = understand.open(db)

for ent in sorted(db.ents("Variable"), key=lambda ent: ent.name()):
    for ref in ent.refs():
        # print(json.JSONEncoder.encode(Reference(ref.kindname(), ent.name(), ref.ent(), ref.column(), ref.line())))
        ref_json = {"kind": ref.kindname(), "mainEntity": ent.name(), "refEntity": ref.ent().name(), "column": ref.column(),
                    "line": ref.line()}
        print(ref_json,'\r')
